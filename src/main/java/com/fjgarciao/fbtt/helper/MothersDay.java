package com.fjgarciao.fbtt.helper;

import com.fjgarciao.fbtt.util.CalendarUtils;
import com.neovisionaries.i18n.CountryCode;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * Source: https://en.wikipedia.org/wiki/Mother%27s_Day
 */
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
            case BY: // Belarus
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
            case RU: // Russia
            case RS: // Serbia
            case TJ: // Tajikistan
            case UZ: // Uzbekistan
            case VN: // Vietnam
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
            case SS: // South Sudan
            case SY: // Syria
            case AE: // United Arab Emirates
            case YE: // Yemen
                c = CalendarUtils.prepareCalendar(year, Calendar.MARCH, 21);
                break;
            case DZ: // Algeria
            case CM: // Cameroon
            case DO: // Dominican Republic
            case MG: // Madagascar
            case ML: // Mali
            case MA: // Morocco
            case NE: // Niger
            case HT: // Haiti
            case MU: // Mautitius
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
        }
        return c == null ? Optional.empty() : Optional.of(c.getTime());
    }
}
