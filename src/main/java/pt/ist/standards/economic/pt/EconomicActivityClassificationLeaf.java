/* 
* @(#)EconomicActivityClassificationLeaf.java 
* 
* Copyright 2012 Instituto Superior Tecnico 
* Founding Authors: Luis Cruz, Susana Fernandes 
*  
*      https://fenix-ashes.ist.utl.pt/ 
*  
*   This file is part of the JodaFinance library. 
* 
*   The JodaFinance library is free software: you can 
*   redistribute it and/or modify it under the terms of the GNU Lesser General 
*   Public License as published by the Free Software Foundation, either version  
*   3 of the License, or (at your option) any later version. 
* 
*   JodaFinance is distributed in the hope that it will be useful, 
*   but WITHOUT ANY WARRANTY; without even the implied warranty of 
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
*   GNU Lesser General Public License for more details. 
* 
*   You should have received a copy of the GNU Lesser General Public License 
*   along with JodaFinance. If not, see <http://www.gnu.org/licenses/>. 
*  
*/
package pt.ist.standards.economic.pt;

/**
 * 
 * @author Luis Cruz
 * @author Susana Fernandes
 * 
 */
public class EconomicActivityClassificationLeaf extends EconomicActivityClassification {

    public final EconomicActivityClassificationGroup group;

    public EconomicActivityClassificationLeaf(final String code, final String description,
            final EconomicActivityClassificationGroup group) {
        super(code, description);
        this.group = group;
        group.classifications.add(this);
    }

    @Override
    public String exportAsString() {
        return group.exportAsString() + ":" + super.exportAsString();
    }

    public EconomicActivityClassificationGroup getGroup() {
        return group;
    }

}
