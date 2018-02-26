package com.privalia.som.springkafka.model;

/**
 * model class for a message
 *
 * @author david.amigo
 */
public class Message {

    /**
     * The text of the message
     */
    private String text;

    /**
     * The optional key of the message
     */
    private String key;

    /**
     * Default constructor
     */
    public Message() {
        this.text = null;
        this.key = null;
    }

    /**
     * Constructor
     *
     * @param text the text of the message
     */
    public Message(String text) {
        this.text = text;
        this.key = null;
    }

    /**
     * Constructor
     *
     * @param text the text of the message
     * @param key the key of the message
     */
    public Message(String text, String key) {
        this.text = text;
        this.key = key;
    }

    /**
     * @return the text of the message
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text of the message
     *
     * @param text the text of the message
     * @return this
     */
    public Message setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * @return the optional key of the message
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key of the message
     *
     * @param key the key of the message
     * @return this
     */
    public Message setKey(String key) {
        this.key = key;
        return this;
    }
}
