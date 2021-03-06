package com.jsandusky.gdx.common;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.io.Serializable;
import java.util.*;

public class TextureHandle implements Serializable {
    public long SourceAtlas; //-1 if none
    public int TextureID; //??
    public String name;
    boolean isAnim;
    transient public TextureRegion region;
    transient public Array<AtlasRegion> sequence;
    
    TextureHandle() {
    }
    
    public TextureHandle(String nm, int id, long source, TextureRegion region) {
    	isAnim = false;
        name = nm;
        SourceAtlas = source;
        TextureID = id;
        this.region = region;
    }
    
    public TextureHandle(String nm, int id, long source, Array<AtlasRegion> seq) {
    	isAnim = true;
        name = nm;
        SourceAtlas = source;
        TextureID = id;
        sequence = seq;
    }
	
	public void load(ImageCache img) {
		if (!isAnim) {
			TextureHandle other = img.getTexture(name);
			region = other.region;
		} else {
			TextureHandle other = img.getFrames(name);
			sequence = other.sequence;
		}
	}
	
	public void load(ResourceManager rm) {
		if (isAnim) {
			TextureHandle other = rm.findRegions(name);
			sequence = other.sequence;
		} else {
			TextureHandle other = rm.findRegion(name);
			region = other.region;
		}
	}
}
