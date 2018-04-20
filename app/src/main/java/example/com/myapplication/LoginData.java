package example.com.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by payawana on 10/13/2017.
 */

public class LoginData {
    @SerializedName("Data")
    @Expose
    private Data data;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("ErrorInfo")
    @Expose
    private List<ErrorInfo> errorInfo = null;
    @SerializedName("Message")
    @Expose
    private Object message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ErrorInfo> getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(List<ErrorInfo> errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public class ErrorInfo {

        @SerializedName("ErrorMessage")
        @Expose
        private String errorMessage;
        @SerializedName("Key")
        @Expose
        private Object key;
        @SerializedName("ErrorCode")
        @Expose
        private String errorCode;

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public Object getKey() {
            return key;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

    }


    public class Data {

        @SerializedName("UserId")
        @Expose
        private Integer userId;
        @SerializedName("RoleId")
        @Expose
        private Integer roleId;
        @SerializedName("RoleName")
        @Expose
        private String roleName;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("UserName")
        @Expose
        private Object userName;
        @SerializedName("Password")
        @Expose
        private Object password;
        @SerializedName("CustomerId")
        @Expose
        private Object customerId;
        @SerializedName("Email")
        @Expose
        private Object email;
        @SerializedName("AuthToken")
        @Expose
        private String authToken;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }
    }
}
