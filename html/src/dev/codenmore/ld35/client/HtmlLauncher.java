package dev.codenmore.ld35.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

import dev.codenmore.ld35.Main;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Main.WIDTH * Main.WINSCALE, Main.HEIGHT * Main.WINSCALE);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Main();
        }
}