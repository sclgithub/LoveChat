package com.techscl.lovechat;

/**
 * Created by dllo on 15/8/29.
 */
public class Msg {
    private String nickname, msg_arrive_time, latest_msg, msg_image;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMsg_arrive_time() {
        return msg_arrive_time;
    }

    public void setMsg_arrive_time(String msg_arrive_time) {
        this.msg_arrive_time = msg_arrive_time;
    }

    public String getLatest_msg() {
        return latest_msg;
    }

    public void setLatest_msg(String latest_msg) {
        this.latest_msg = latest_msg;
    }

    public String getMsg_image() {
        return msg_image;
    }

    public void setMsg_image(String msg_image) {
        this.msg_image = msg_image;
    }
}
