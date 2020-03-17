package Backend;

public class UserModel {


    private String IP;
    private long timeStamp;

    public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}