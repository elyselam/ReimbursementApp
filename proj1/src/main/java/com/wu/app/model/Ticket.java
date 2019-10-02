package com.wu.app.model;

public class Ticket {
    private int ticketID;
    private int submitterID;
    private float cost;
    private String description;
    private int reviewerID;
    private boolean isApproved;

    public Ticket(int ticketID, int submitterID, float cost, String description, int reviewerID, boolean isApproved) {
        this.ticketID = ticketID;
        this.submitterID = submitterID;
        this.cost = cost;
        this.description = description;
        this.reviewerID = reviewerID;
        this.isApproved = isApproved;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getSubmitterID() {
        return submitterID;
    }

    public void setSubmitterID(int submitterID) {
        this.submitterID = submitterID;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
