package com.hod.hodassignment.model;

/**
 * Created by vikas.sunil.barve on 9/20/2017.
 */

public class ChatModel {
        private int senderId;
        private int receiverId;
        private String message;

        public int getSenderId() {
            return senderId;
        }

        public void setSenderId(int senderId) {
            this.senderId = senderId;
        }

        public int getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(int receiverId) {
            this.receiverId = receiverId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
}
