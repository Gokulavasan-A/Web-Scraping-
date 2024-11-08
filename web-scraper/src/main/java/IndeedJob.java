public class IndeedJob {
    private String jobTitle;
    private String jobCompName;
    private String jobLocation;

    // Getters and Setters for jobTitle
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // Getters and Setters for jobCompName
    public String getJobCompName() {
        return jobCompName;
    }

    public void setJobCompName(String jobCompName) {
        this.jobCompName = jobCompName;
    }

    // Getters and Setters for jobLocation
    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    // Optional: Override toString to print job details
    @Override
    public String toString() {
        return "IndeedJob{" +
                "jobTitle='" + jobTitle + '\'' +
                ", jobCompName='" + jobCompName + '\'' +
                ", jobLocation='" + jobLocation + '\'' +
                '}';
    }
}
