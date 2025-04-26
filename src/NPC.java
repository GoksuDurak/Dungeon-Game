public class NPC {
    private String name;
    private String job;
    private String message;
    NPC(String name, String job, String message)
    {
        this.name = name;
        this.job = job;
        this.message = message;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setJob(String job)
    {
        this.job = job;
    }
    public String getJob()
    {
        return job;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
}
