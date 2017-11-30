package com.example.jakers.dissertation_work;

import java.sql.Timestamp;

/**
 * Created by Jakers on 29/11/2017.
 */

public class homeworkResourceObj {
    String resource_description;
    String resource_URL;
    String resource_Intent;

    public void setResource_Timestamp(Timestamp resource_Timestamp) {
        this.resource_Timestamp = resource_Timestamp;
    }

    Timestamp resource_Timestamp;

    public Timestamp getResource_Timestamp() {
        return resource_Timestamp;
    }

    public void setResource_description(String resource_description) {
        this.resource_description = resource_description;
    }

    public void setResource_URL(String resource_URL) {
        this.resource_URL = resource_URL;
    }

    public void setResource_Intent(String resource_Intent) {
        this.resource_Intent = resource_Intent;
    }

    public String getResource_description() {

        return resource_description;
    }

    public String getResource_URL() {
        return resource_URL;
    }

    public String getResource_Intent() {
        return resource_Intent;
    }
}
