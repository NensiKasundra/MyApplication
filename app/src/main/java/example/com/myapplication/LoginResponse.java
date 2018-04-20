package example.com.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kasundne on 12/8/2017.
 */

class LoginResponse {
    @SerializedName("IsValid Login")
    @Expose
    private String isValidLogin;
    @SerializedName("Status Code")
    @Expose
    private String statusCode;
    @SerializedName("Workshop Id")
    @Expose
    private String workshopId;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Workshop Name")
    @Expose
    private String workshopName;
    @SerializedName("PassCode")
    @Expose
    private String passCode;
    @SerializedName("Workshop Email")
    @Expose
    private String workshopEmail;
    @SerializedName("oAuthkey")
    @Expose
    private String oAuthkey;
    @SerializedName("Roles")
    @Expose
    private List<String> roles = null;
    @SerializedName("AppConfiguration")
    @Expose
    private AppConfiguration appConfiguration;

    public String getIsValidLogin() {
        return isValidLogin;
    }

    public void setIsValidLogin(String isValidLogin) {
        this.isValidLogin = isValidLogin;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getPassCode() {
        return passCode;
    }

    public void setPassCode(String passCode) {
        this.passCode = passCode;
    }

    public String getWorkshopEmail() {
        return workshopEmail;
    }

    public void setWorkshopEmail(String workshopEmail) {
        this.workshopEmail = workshopEmail;
    }

    public String getOAuthkey() {
        return oAuthkey;
    }

    public void setOAuthkey(String oAuthkey) {
        this.oAuthkey = oAuthkey;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public AppConfiguration getAppConfiguration() {
        return appConfiguration;
    }

    public void setAppConfiguration(AppConfiguration appConfiguration) {
        this.appConfiguration = appConfiguration;
    }

    public class AppConfiguration {

        @SerializedName("DataDeleteHrs")
        @Expose
        private String dataDeleteHrs;
        @SerializedName("MaxFileSize")
        @Expose
        private String maxFileSize;
        @SerializedName("MaxTotalFileSize")
        @Expose
        private String maxTotalFileSize;
        @SerializedName("TimeOut")
        @Expose
        private String timeOut;
        @SerializedName("Version")
        @Expose
        private String version;

        public String getDataDeleteHrs() {
            return dataDeleteHrs;
        }

        public void setDataDeleteHrs(String dataDeleteHrs) {
            this.dataDeleteHrs = dataDeleteHrs;
        }

        public String getMaxFileSize() {
            return maxFileSize;
        }

        public void setMaxFileSize(String maxFileSize) {
            this.maxFileSize = maxFileSize;
        }

        public String getMaxTotalFileSize() {
            return maxTotalFileSize;
        }

        public void setMaxTotalFileSize(String maxTotalFileSize) {
            this.maxTotalFileSize = maxTotalFileSize;
        }

        public String getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(String timeOut) {
            this.timeOut = timeOut;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

    }
}
