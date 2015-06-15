/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.rpgtoolkit.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Chris Hutchinson <chris@cshutchinson.com>
 */
public class Engine {

    public Engine() {
        this.states = new Stack<>();
        this.listeners = new ArrayList<>();
    }

    public void push(State state) {
        this.states.push(state);
    }

    public void pop() {
        this.states.pop();
    }

    public void reset() {
        this.states.clear();
    }

    public void update() {

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (this.states.size() == 0) {
            return;
        }
        
        final State state = this.states.peek();
        
        if (state != null) {
            state.update();
            state.render();
        }
        
    }

    private final List<EngineListener> listeners;
    private final Stack<State> states;

}
