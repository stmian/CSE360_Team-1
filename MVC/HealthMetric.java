import java.util.Date;

 class HealthMetric {

        int id;
        int typeId;
        String typeName;
        double metric;
        Date date;

        public HealthMetric(int id, int typeId, String typeName, double metric, Date date) {
            this.id = id;
            this.typeId = typeId;
            this.typeName = typeName;
            this.metric = metric;
            this.date = date;
        } //__constructor
    } //HealthMetric