package syb.tools;

/**
 * @author Lenovo
 */
public class PopDialog {
    /**弹出的提示信息*/
    private String message;

    /**重定向的链接地址*/
    private String url;
    public String getDialogStr(){
        //弹出对话框的代码
        String dialogStr = "<script language='javascript'>\r\n\t";
        //弹出对话框
        dialogStr += "alert('" + message + "');\r\n\t";
        //页面重定向
        dialogStr += "window.location.href = '" + url + "';\r\n";
        dialogStr += "</script>";
        return dialogStr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
