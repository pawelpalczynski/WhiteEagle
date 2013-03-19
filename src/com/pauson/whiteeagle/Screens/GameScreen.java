package com.pauson.whiteeagle.Screens;

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pauson.whiteeagle.WhiteEagle;

public class GameScreen implements Screen {
	
	public static final float GdxToBox2D = 1/5f;
	public static final float Box2DToGdx = 5f;
	float width, height;
	
	WhiteEagle game;
	World world;
	OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer;
	FPSLogger logger;
	
	Body circleBody;
	
	RayHandler rayHandler;
	
	public GameScreen(WhiteEagle game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		debugRenderer.render(world, camera.combined);
		
		rayHandler.updateAndRender();
		
		world.step(1/60f, 8, 3);
		
		logger.log();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -9.8f), true);
		
		width = Gdx.graphics.getWidth()*GdxToBox2D;
		height = Gdx.graphics.getHeight()*GdxToBox2D;
		
		camera = new OrthographicCamera(width, height);
		camera.position.set(width * 0.5f, height * 0.5f, 0);
		
		debugRenderer = new Box2DDebugRenderer();
		
		logger = new FPSLogger();
		
		BodyDef circleDef = new BodyDef();
		circleDef.type = BodyType.DynamicBody;
		circleDef.position.set(0, 0);
		
		circleBody = world.createBody(circleDef);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(3f);
		
		FixtureDef circleFixture = new FixtureDef();
		circleFixture.shape = circleShape;
		circleFixture.density = 0.4f;
		circleFixture.restitution = 0.5f;
		circleFixture.friction = 0.8f;
		
		circleBody.createFixture(circleFixture);
		
		BodyDef groundDef = new BodyDef();
		groundDef.type = BodyType.StaticBody;
		groundDef.position.set(0, -camera.viewportHeight * 0.5f);
		
		Body groundBody = world.createBody(groundDef);
		
		PolygonShape groundShape = new PolygonShape();
		groundShape.setAsBox(camera.viewportWidth * 0.5f, 3f);
		
		groundBody.createFixture(groundShape, 0);
		
		RayHandler.setColorPrecisionMediump();
		RayHandler.setGammaCorrection(true);
		rayHandler = new RayHandler(world);
		rayHandler.setCombinedMatrix(camera.combined);
		rayHandler.setCulling(true);
//		rayHandler.setAmbientLight(0.0f,0.6f,0.6f,0.4f);
		
//		new PointLight(rayHandler, 4096, Color.CYAN, 400f, -30f, 0);
		new ConeLight(rayHandler, 4000, Color.ORANGE, 400f, 20f, 0, -90, 40);
//		(new PointLight(rayHandler, 1024)).attachToBody(circleBody, 0, 0);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		world.dispose();
		rayHandler.dispose();
	}

}
