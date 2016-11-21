package com.whfrp3.model;

import android.databinding.BaseObservable;

import java.io.Serializable;

/**
 * Abstract class extended by each class of the model using technical identifier.
 */
public class AbstractModel extends BaseObservable implements Serializable {

    /**
     * Technical identifier.
     */
    protected long id;

    /**
     * Getter of the technical identifier.
     *
     * @return Technical identifier.
     */
    public long getId() {
        return id;
    }

    /**
     * Setter of the technical identifier.
     *
     * @param newId New technical identifier.
     */
    public void setId(long newId) {
        this.id = newId;
    }
}
