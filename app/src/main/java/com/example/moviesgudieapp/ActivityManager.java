package com.example.moviesgudieapp;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {

    public ActivityManager() {
    }

    private static ActivityManager activityManager = new ActivityManager();

    public static ActivityManager getActivityManager() {
        return activityManager;
    }

    private static Stack<Activity> activityStack = new Stack<>();

    //add the activity into the stack
    public void addActivityManager(Activity activity) {
        if (activity != null) {
            activityStack.add(activity);
        }
    }

    //delete the activityStack now
    public void removeCurrent() {
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    //remove the specific activity
    public void remove(Activity activity) {
        if (activity != null) {
            for (int i = activityStack.size() - 1; i >= 0; i--) {
                Activity currentActivity = activityStack.get(i);
                if (currentActivity.getClass().equals(activity.getClass())) {
                    currentActivity.finish();//destroy the activity now
                    activityStack.remove(i);//delete the activity in the stack
                }
            }
        }
    }

    //remove all the activities
    public void removeAll(Activity activity) {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            activity.finish();
            activityStack.remove(i);
        }
    }

    //return the length of the stack
    public int size() {
        return activityStack.size();
    }
}



