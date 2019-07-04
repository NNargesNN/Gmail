package Common;

import java.io.Serializable;
import java.util.Set;

public class UserMail implements Serializable {
    private static final long serialVersionUID = 10001L;
    private Gmail gmail;
    private Set<MailType> mailTypeSet;


    public Gmail getGmail() {

        return gmail;
    }


    public UserMail(Gmail gmail, Set<Common.MailType> mailTypeSet) {

        this.gmail = gmail;
        this.mailTypeSet = mailTypeSet;
    }


    public UserMail(Gmail gmail) {

        this.gmail = gmail;
    }


    public void setGmail(Gmail gmail) {

        this.gmail = gmail;
    }


    public Set<MailType> getMailTypeSet() {

        return mailTypeSet;
    }


    public void setMailTypeSet(Set<MailType> mailTypeSet) {

        this.mailTypeSet = mailTypeSet;
    }

}
