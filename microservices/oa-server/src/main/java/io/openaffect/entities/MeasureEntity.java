package io.openaffect.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.openaffect.api.model.Emotion;
import io.openaffect.api.model.Resource;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "measure")
public class MeasureEntity {
    @Id
    @JsonProperty
    private String id = null;

    @JsonProperty("timestamp")
    private DateTime timestamp = null;

    @JsonProperty("emotion")
    private Emotion emotion = null;

    @JsonProperty("subject")
    private Resource subject = null;

    @JsonProperty("trigger")
    private Resource trigger = null;

    @JsonProperty("sensor")
    private Resource sensor = null;

    public String getId() {
        return id;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public Resource getSubject() {
        return subject;
    }

    public void setSubject(Resource subject) {
        this.subject = subject;
    }

    public Resource getTrigger() {
        return trigger;
    }

    public void setTrigger(Resource trigger) {
        this.trigger = trigger;
    }

    public Resource getSensor() {
        return sensor;
    }

    public void setSensor(Resource sensor) {
        this.sensor = sensor;
    }
}
