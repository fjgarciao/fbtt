package com.fjgarciao.fbtt.component;

import com.fjgarciao.fbtt.util.CalendarUtils;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * Source: https://en.wikipedia.org/wiki/Mother%27s_Day
 */
@Component
public class MothersDay implements MarketingDay {

    @Override
    public String getName() {
        return "MOTHERS_DAY";
    }

    @Override
    public Optional<Date> getDate(CountryCode countryCode, int year) {
        Calendar c = null;
        switch (countryCode) {
            case NO: // Norway
                // Second Sunday of February
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.FEBRUARY);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
                break;
            case GE: // Georgia
                c = CalendarUtils.prepareCalendar(year, Calendar.MARCH, 3);
                break;
            case AF: // Afghanistan
            case AL: // Albania
            case AZ: // Azerbaijan
            case BA: // Bosnia & Herzegovina
            case BG: // Bulgaria
            case BI: // Burundi
            case BF: // Burkina Faso
            case KZ: // Kazakhstan
            case XK: // Kosovo
            case LA: // Laos
            case MK: // Macedonia
            case MD: // Moldova
            case ME: // Montenegro
            case RS: // Serbia
            case TJ: // Tajikistan
            case UZ: // Uzbekistan
                c = CalendarUtils.prepareCalendar(year, Calendar.MARCH, 8);
                break;
            case GG: // Guernsey
            case IE: // Ireland
            case IM: // Isle of Man
            case JE: // Jersey
            case NG: // Nigeria
            case UK: // United Kingdom
                // Fourth Sunday in Lent (Mothering Sunday)
                c = CalendarUtils.getEasterDate(year);
                c.add(Calendar.WEEK_OF_YEAR, -3);
                break;
            case BH: // Bahrain
            case KM: // Comoros
            case DJ: // Djibouti
            case EG: // Egypt
            case IQ: // Iraq
            case JO: // Jordan
            case KW: // Kuwait
            case LY: // Libya
            case LB: // Lebanon
            case MR: // Mauritania
            case OM: // Oman
            case PS: // Palestine
            case QA: // Qatar
            case SA: // Saudi Arabia
            case SO: // Somalia
            case SD: // Sudan
            case SY: // Syria
            case AE: // United Arab Emirates
            case YE: // Yemen
                c = CalendarUtils.prepareCalendar(year, Calendar.MARCH, 21);
                break;
            case SI: // Slovenia
                c = CalendarUtils.prepareCalendar(year, Calendar.MARCH, 25);
                break;
            case AM: // Armenia
                c = CalendarUtils.prepareCalendar(year, Calendar.APRIL, 7);
                break;
            case AO: // Angola
            case CV: // Cape Verde
            case HU: // Hungary
            case LT: // Lithuania
            case MZ: // Mozambique
            case PT: // Portugal
            case RO: // Romania
            case ES: // Spain
                // First Sunday of May
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.MAY);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                break;
            case KR: // South Korea
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 8);
                break;
            case SV: // El Salvador
            case GT: // Guatemala
            case MX: // Mexico
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 10);
                break;
            case AI: // Anguilla
            case AG: // Antigua & Barbuda
            case AW: // Aruba
            case AU: // Australia
            case AT: // Austria
            case BS: // Bahamas
            case BD: // Bangladesh
            case BB: // Barbados
            case BE: // Belgium
            case BZ: // Belize
            case BM: // Bermuda
            case BT: // Bhutan
            case BQ: // Bonaire
            case BW: // Botswana
            case BR: // Brazil
            case BN: // Brunei
            case CA: // Canada
            case KH: // Cambodia
            case KY: // Cayman Islands
            case CF: // Central African Republic
            case TD: // Chad
            case CL: // Chile
            case CN: // China
            case CO: // Colombia
            case CD: // Congo, Dem. Rep.
            case CG: // Congo, Rep.
            case CI: // Core d'Ivoire
            case HR: // Croatia
            case CU: // Cuba
            case CW: // Curaçao
            case CY: // Cyprus
            case CZ: // Czech Rep.
            case DK: // Denmark
            case DM: // Dominica
            case EC: // Ecuador
            case GQ: // Equatorial Guinea
            case EE: // Estonia
            case ET: // Ethiopia
            case FO: // Faroe Islands
            case FJ: // Fiji
            case FI: // Finland
            case DE: // Germany
            case GA: // Gabon
            case GM: // Gambia
            case GL: // Greenland
            case GH: // Ghana
            case GR: // Greece
            case GD: // Grenada
            case GY: // Guyana
            case HN: // Honduras
            case HK: // Hong Kong
            case IS: // Iceland
            case IN: // India
            case IT: // Italy
            case JM: // Jamaica
            case JP: // Japan
            case KE: // Kenya
            case LV: // Latvia
            case LR: // Liberia
            case LI: // Liechtenstein
            case MO: // Macao
            case MY: // Malaysia
            case MT: // Malta
            case MM: // Myanmar
            case NA: // Namibia
            case NL: // Netherlands
            case NZ: // New Zealand
            case PK: // Pakistan
            case PG: // Papua New Guinea
            case PE: // Peru
            case PH: // Philippines
            case PR: // Puerto Rico
            case KN: // Saint Kitts & Nevis
            case LC: // Saint Lucia
            case VC: // Saint Vincent & The Grenadines
            case WS: // Samoa
            case SG: // Singapore
            case SX: // Sint Maarten
            case SK: // Slovakia
            case ZA: // South Africa
            case LK: // Sri Lanka
            case SR: // Suriname
            case CH: // Switzerland
            case TW: // Taiwan
            case TZ: // Tanzania
            case TO: // Tonga
            case TT: // Trinidad & Tobago
            case TR: // Turkey
            case UG: // Uganda
            case UA: // Ukraine
            case US: // United States
            case UY: // Uruguay
            case VN: // Vietnam
            case VE: // Venezuela
            case ZM: // Zambia
            case ZW: // Zimbabwe
                // Second Sunday of May
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.MAY);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
                break;
            case BJ: // Benin
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 14);
                break;
            case PY: // Paraguay
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 15);
                break;
            case KG: // Kyrgyzstan
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 19);
                break;
            case IL: // Israel
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 22);
                break;
            case PL: // Poland
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 26);
                break;
            case BO: // Bolivia
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 27);
                break;
            case DZ: // Algeria
            case CM: // Cameroon
            case DO: // Dominican Republic
            case MG: // Madagascar
            case ML: // Mali
            case MA: // Morocco
            case NE: // Niger
            case HT: // Haiti
            case MU: // Mauritius
            case SN: // Senegal
            case SE: // Sweden
            case TN: // Tunisia
                // Last Sunday of May
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.MAY);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 4);
                if (c.get(Calendar.DAY_OF_MONTH) <= 24)
                    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 5);
                break;
            case FR: // France
            case GP: // Guadalupe
            case MQ: // Martinique
                // Last Sunday of May, except when it matches Pentecost, then it is First Sunday of June
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.MAY);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 4);
                if (c.get(Calendar.DAY_OF_MONTH) <= 24)
                    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 5);

                Calendar pentecost = CalendarUtils.getPentecostDate(year);
                if (pentecost.getTime().equals(c.getTime())) {
                    c.add(Calendar.WEEK_OF_YEAR, 1);
                }
                break;
            case NI: // Nicaragua
                c = CalendarUtils.prepareCalendar(year, Calendar.MAY, 30);
                break;
            case MN: // Mongolia
                c = CalendarUtils.prepareCalendar(year, Calendar.JUNE, 1);
                break;
            case LU: // Luxembourg
                // Second Sunday of June
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.JUNE);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
                break;
            case SS: // South Sudan
                // First Monday of July
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.JULY);
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
                break;
            case TH: // Thailand
                c = CalendarUtils.prepareCalendar(year, Calendar.AUGUST, 12);
                break;
            case CR: // Costa Rica
                c = CalendarUtils.prepareCalendar(year, Calendar.AUGUST, 15);
                break;
            case MW: // Malawi
                // Second Monday of October
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.OCTOBER);
                c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 2);
                break;
            case BY: // Belarus
                c = CalendarUtils.prepareCalendar(year, Calendar.OCTOBER, 14);
                break;
            case AR: // Argentina
                // Third Sunday of October
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.OCTOBER);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 3);
                break;
            case TL: // Timor-Leste
                c = CalendarUtils.prepareCalendar(year, Calendar.NOVEMBER, 3);
                break;
//          case KP: // North Korea: UNALLOWED
//              c = CalendarUtils.prepareCalendar(year, Calendar.NOVEMBER, 16);
//              break;
            case RU: // Russia
                // Last Sunday of November
                c = CalendarUtils.prepareCalendar(year);
                c.set(Calendar.MONTH, Calendar.NOVEMBER);
                c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 4);
                if (c.get(Calendar.DAY_OF_MONTH) <= 23)
                    c.set(Calendar.DAY_OF_WEEK_IN_MONTH, 5);
                break;
            case PA: // Panama
                c = CalendarUtils.prepareCalendar(year, Calendar.DECEMBER, 8);
                break;
            case ID: // Indonesia
                c = CalendarUtils.prepareCalendar(year, Calendar.DECEMBER, 22);
                break;
            case NP: // Nepal
                switch(year) {
                    case 2017:
                        c = CalendarUtils.prepareCalendar(year, Calendar.APRIL, 26);
                        break;
                    case 2018:
                        c = CalendarUtils.prepareCalendar(year, Calendar.APRIL, 21);
                        break;
                }
                break;
            case IR: // Iran
                switch(year) {
                    case 2017:
                        c = CalendarUtils.prepareCalendar(year, Calendar.FEBRUARY, 28);
                        break;
                    case 2018:
                        c = CalendarUtils.prepareCalendar(year, Calendar.FEBRUARY, 17);
                        break;
                    case 2019:
                        c = CalendarUtils.prepareCalendar(year, Calendar.FEBRUARY, 6);
                        break;
                    case 2020:
                        c = CalendarUtils.prepareCalendar(year, Calendar.JANUARY, 26);
                        break;
                    case 2021:
                        c = CalendarUtils.prepareCalendar(year, Calendar.JANUARY, 14);
                        break;
                }
                c.add(Calendar.DATE, 20);
                break;
        }
        return c == null ? Optional.empty() : Optional.of(c.getTime());
    }
}
