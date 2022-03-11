package pt.ist.standards.geographic;

import pt.ist.standards.util.ResourceReader;

class DataLoader {

    private static final String RESOURCE_PATH = "/geographic/";

    static Planet init() {
        final Planet planet = new Planet("Earth");
        loadCountryData(planet);
        loadSpecificCountryDate(planet);
        return planet;
    }

    private static void loadCountryData(final Planet planet) {
        final String[] content = ResourceReader.readLines(RESOURCE_PATH + "countries.txt");
        for (final String line : content) {
            final String[] ss = line.split("\t");
            final String alpha2 = ss[0];
            final String alpha3 = ss[1];
            final String number = ss[2];
            final String name = ss[3];
            new Country(planet, name, alpha2, alpha3, number);
        }
    }

    private static void loadSpecificCountryDate(final Planet planet) {
        loadSpecificCountryDate(planet, "PRT");
    }

    private static void loadSpecificCountryDate(final Planet planet, final String alfa3) {
        final Country country = planet.getPlace(alfa3);
        final String resourcePrefix = RESOURCE_PATH + alfa3.toLowerCase();
        loadDistricts(country, resourcePrefix);
        loadMunicipalities(country, resourcePrefix);
        loadPostalCodes(country, resourcePrefix);
        loadParishes(country, resourcePrefix);
    }

    private static void loadDistricts(final Country country, final String resourcePrefix) {
        final String[] content = ResourceReader.readLines(resourcePrefix + "/districts.txt");
        for (int i = content.length; i-- > 0;) {
            final String line = content[i];
            final int s = line.indexOf(';');
            if (s > 0) {
                final String code = line.substring(0, s);
                final String name = line.substring(s + 1);
                new District(country, name, code);
            }
        }
    }

    private static void loadMunicipalities(final Country country, final String resourcePrefix) {
        final String[] content = ResourceReader.readLines(resourcePrefix + "/municipalities.txt");
        for (final String line : content) {
            final int s1 = line.indexOf(';');
            final int s2 = line.indexOf(';', s1 + 1);
            if (s2 > 0) {
                final String districtKey = line.substring(0, s1);
                final String code = line.substring(s1 + 1, s2);
                final String name = line.substring(s2 + 1);
                final District district = country.getPlace(districtKey);
                new Municipality(district, name, code);
            }
        }
    }

    private static void loadPostalCodes(final Country country, final String resourcePrefix) {
        final String[] content = ResourceReader.readLines(resourcePrefix + "/postalCodes.txt");
        for (final String line : content) {
            final int s1 = line.indexOf(';');
            final int s2 = line.indexOf(';', s1 + 1);
            final int s3 = line.indexOf(';', s2 + 1);
            final int s4 = line.indexOf(';', s3 + 1);
            final int s5 = line.indexOf(';', s4 + 1);
            final int s6 = line.indexOf(';', s5 + 1);
            final int s7 = line.indexOf(';', s6 + 1);
            final int s8 = line.indexOf(';', s7 + 1);
            final int s9 = line.indexOf(';', s8 + 1);
            final int s10 = line.indexOf(';', s9 + 1);
            final int s11 = line.indexOf(';', s10 + 1);
            final int s12 = line.indexOf(';', s11 + 1);
            final int s13 = line.indexOf(';', s12 + 1);
            final int s14 = line.indexOf(';', s13 + 1);
            final int s15 = line.indexOf(';', s14 + 1);
            final int s16 = line.indexOf(';', s15 + 1);

            final String districtKey = line.substring(0, s1);
            final String municipalityKey = line.substring(s1 + 1, s2);
            final String localityKey = line.substring(s2 + 1, s3);
            final String localityName = line.substring(s3 + 1, s4);
            final String postalCodeKey = line.substring(s14 + 1, s15);
            final String extension = line.substring(s15 + 1, s16);
            final String vCodigoPostal = postalCodeKey + "-" + extension;
            final String postalCodeName = line.substring(s16 + 1);

            final District district = country.getPlace(districtKey);
            final Municipality municipality = district.getPlace(municipalityKey);
            Locality locality = municipality.getPlace(localityKey);
            if (locality == null) {
                locality = new Locality(municipality, localityName, localityKey);
            }
            PostalCode postalCode = locality.getPlace(vCodigoPostal);
            if (postalCode == null) {
                postalCode = new PostalCode(locality, postalCodeName, vCodigoPostal);
            }
            final String streetName = line.substring(s5 + 1, s10).replace(';', ' ').replaceAll("( )+", " ").trim();
            if (!streetName.isEmpty()) {
                postalCode.streets.add(streetName);
            }
        }
    }

    private static void loadParishes(final Country country, final String resourcePrefix) {
        final String[] content = ResourceReader.readLines(resourcePrefix + "/parishes.txt");
        for (final String line : content) {
            final int s1 = line.indexOf(';');
            final int s2 = line.indexOf(';', s1 + 1);
            final int s3 = line.indexOf(';', s2 + 1);
            final int s4 = line.indexOf(';', s3 + 1);
            final int s5 = line.indexOf(';', s4 + 1);
            final int s6 = line.indexOf(';', s5 + 1);

            final String districtKey = line.substring(s1 + 1, s2);
            final String municipalityKey = line.substring(s3 + 1, s4);
            final String parishKey = line.substring(s5 + 1, s6);
            final String parishName = line.substring(s6 + 1);

            final District district = country.getPlace(districtKey);
            if (district == null) {
                System.out.println("no district for: " + districtKey);
            } else {
                final Municipality municipality = district.getPlace(municipalityKey);
                if (municipality == null) {
                    System.out.println("no municipality for: " + districtKey + " " + municipalityKey);
                } else {
                    new Parish(municipality, parishName, parishKey);
                }
            }
        }
    }

}
