enum ClientType {
    NEW(0){
        @Override
        public double discount() {
            return (100-getMonths()*0.35)/100;
        }
    },
    SILVER(12){
        @Override
        public double discount() {
            return (100-getMonths()*0.35)/100;
        }
    },
    GOLD(30){
        @Override
        public double discount() {
            return (100-getMonths()*0.35)/100;
        }
    },
    PLATINUM(60){
        @Override
        public double discount() {
            return (100-getMonths()*0.35)/100;
        }
    };
    private int months ;

    public int getMonths() {
        return months;
    }

    public double discount(){
        return 1.0;
    }

    ClientType (int months){
        this.months=months;
    }
}
