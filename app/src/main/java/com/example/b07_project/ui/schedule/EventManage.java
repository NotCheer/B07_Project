package com.example.b07_project.ui.schedule;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EventManage {
    private List<Event> eventList;
    private EventManage(){
        if(eventList == null)
            eventList = new ArrayList<Event>();
    }


}
