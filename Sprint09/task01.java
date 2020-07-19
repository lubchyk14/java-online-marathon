public static boolean isLeapYear(int year) {
        LocalDate ld = LocalDate.of(year,01,01);
        return ld.isLeapYear();

    }