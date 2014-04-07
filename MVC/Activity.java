import java.util.Date;

/**
 * Data structure class for activities
 *
 * @author Brenden
 */
class Activity {
    int id;
    int typeId;
    String typeName;
    Date date;
    double duration;

    public Activity(int id, int typeId, String typeName, Date date, double duration) {
        this.id = id;
        this.typeId = typeId;
        this.typeName = typeName;
        this.date = date;
        this.duration = duration;
    } //__constructor
} //Activity
