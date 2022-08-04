

public class Log {

    String title;
    String logContent;
    int id;
    String timetamp;

    public Log(int id,String title,String logContent){
        this.id=id;
        this.title=title;
        this.logContent=logContent;
        //timetamp=DateTimeFormatter.ofPattern("dd,mm,yyyy HH,mm,ss").format(LocalDateTime.now());
    }
    
    public Log(int id,String title,String logContent, String time){
        this.id=id;
        this.title=title;
        this.logContent=logContent;
        this.timetamp=time;
        //timetamp=DateTimeFormatter.ofPattern("dd,mm,yyyy HH,mm,ss").format(LocalDateTime.now());
    }
    
    public Log(String title,String logContent){
        this.title=title;
        this.logContent=logContent;
        //timetamp=DateTimeFormatter.ofPattern("dd,mm,yyyy HH,mm,ss").format(LocalDateTime.now());
    }
    
    
    
    /*
    Builders pattern
    Setters
 */
    public Log setTitle(String title) {
        this.title = title;
        return this;
    }

    public Log setLogContent(String logContent) {
        this.logContent = logContent;
        return this;
    }

    public Log setId(int id) {
        this.id = id;
        return this;
    }

    public Log setTimetamp(String timstamp) {
        this.timetamp = timstamp;
        return this;
    }

    
    /*
    Getters
    */
    public String getTitle() {
        return title;
    }

    public String getLogContent() {
        return logContent;
    }

    public int getId() {
        return id;
    }

    public String getTimetamp() {
        return timetamp;
    }
    

    
    
    
    
    
    
    /*
        Abstract functions
    
    public abstract void create();
    public abstract void read();
    public abstract void update();
    public abstract void delete();
    */
    
}
