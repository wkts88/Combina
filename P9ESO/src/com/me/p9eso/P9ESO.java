package com.me.p9eso;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.me.p9eso.game.WorldController;
import com.me.p9eso.game.WorldRenderer;

public class P9ESO implements ApplicationListener {
	private static final String TAG = P9ESO.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;
	
	@Override
	public void create() 
	{
		//Cambiar a LOG_NONE antes de publicar
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		
		paused = false;
	}

	@Override
	public void dispose() 
	{
		worldRenderer.dispose();
	}

	@Override
	public void render() 
	{
		//Actualiza el mundo
		if(!paused)
		{
			worldController.update(Gdx.graphics.getDeltaTime());
		}
		
		//Ponemos como color para limpiar la pantalla uno +- azul
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		
		//Limpia
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//Render
		worldRenderer.render();
	}

	@Override
	public void resize(int width, int height)
	{
		worldRenderer.resize(width, height);
	}

	@Override
	public void pause()
	{
		paused = true;
	}

	@Override
	public void resume() 
	{
		paused = false;
	}
}
