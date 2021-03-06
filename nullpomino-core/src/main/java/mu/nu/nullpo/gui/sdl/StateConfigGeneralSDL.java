/*
    Copyright (c) 2010, NullNoname
    All rights reserved.

    Redistribution and use in source and binary forms, with or without
    modification, are permitted provided that the following conditions are met:

        * Redistributions of source code must retain the above copyright
          notice, this list of conditions and the following disclaimer.
        * Redistributions in binary form must reproduce the above copyright
          notice, this list of conditions and the following disclaimer in the
          documentation and/or other materials provided with the distribution.
        * Neither the name of NullNoname nor the names of its
          contributors may be used to endorse or promote products derived from
          this software without specific prior written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
    ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
    LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
    CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
    SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
    INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
    CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
    POSSIBILITY OF SUCH DAMAGE.
*/
package mu.nu.nullpo.gui.sdl;

import mu.nu.nullpo.util.CustomProperties;
import mu.nu.nullpo.util.GeneralUtil;

import sdljava.SDLException;
import sdljava.video.SDLSurface;

/**
 * State of the general settings screen
 */
public class StateConfigGeneralSDL extends BaseStateSDL {
	/** UI Text identifier Strings */
	protected static final String[] UI_TEXT = {
		"ConfigGeneral_SE",
		"ConfigGeneral_BGM",
		"ConfigGeneral_BGMPreload",
		"ConfigGeneral_SEVolume",
		"ConfigGeneral_BGMVolume",
		"ConfigGeneral_Background",
		"ConfigGeneral_UseBackgroundFade",
		"ConfigGeneral_ShowLineEffect",
		"ConfigGeneral_LineEffectSpeed",
		"ConfigGeneral_ShowMeter",
		"ConfigGeneral_DarkNextArea",
		"ConfigGeneral_NextShadow",
		"ConfigGeneral_NextType",
		"ConfigGeneral_OutlineGhost",
		"ConfigGeneral_FieldBGBright",
		"ConfigGeneral_ShowFieldBGGrid",
		"ConfigGeneral_ShowInput",
		"ConfigGeneral_Fullscreen",
		"ConfigGeneral_ShowFPS",
		"ConfigGeneral_MaxFPS",
		"ConfigGeneral_FrameStep",
		"ConfigGeneral_PerfectFPS",
		"ConfigGeneral_PerfectYield",
		"ConfigGeneral_SoundBufferSize",
		"ConfigGeneral_SoundChannels",
	};

	/** Piece preview type options */
	protected static final String[] NEXTTYPE_OPTIONS = {"TOP", "SIDE(SMALL)", "SIDE(BIG)"};

	/** Cursor position */
	protected int cursor;

	/** Full screen flag */
	protected boolean fullscreen;

	/** Sound effectsON/OFF */
	protected boolean se;

	/** BGMOfON/OFF */
	protected boolean bgm;

	/** BGMPreloading of */
	protected boolean bgmpreload;

	/** BackgroundDisplay */
	protected boolean showbg;

	/** FPSDisplay */
	protected boolean showfps;

	/**  frame Step is enabled */
	protected boolean enableframestep;

	/** MaximumFPS */
	protected int maxfps;

	/** Line clearDisplay Effects */
	protected boolean showlineeffect;

	/** Line clear effect speed */
	protected int lineeffectspeed;

	/** Sound buffer size */
	protected int soundbuffer;

	/** Heavy production use */
	protected boolean heavyeffect;

	/** fieldBackgroundThe brightness of the */
	protected int fieldbgbright;

	/** Show field BG grid */
	protected boolean showfieldbggrid;

	/** NEXTDarken the field */
	protected boolean darknextarea;

	/** Sound effects volume */
	protected int sevolume;

	/** BGM volume */
	protected int bgmvolume;

	/** You can play simultaneouslySound effectsOfcount */
	protected int soundChannels;

	/** fieldTo the rightMeterShow */
	protected boolean showmeter;

	/** ghost On top of the pieceNEXTDisplay */
	protected boolean nextshadow;

	/** Linear frameghost Peace */
	protected boolean outlineghost;

	/** Piece preview type (0=Top 1=Side small 2=Side big) */
	protected int nexttype;

	/** Side piece preview */
	protected boolean sidenext;

	/** Bigger side piece preview */
	protected boolean bigsidenext;

	/** True to use perfect FPS */
	protected boolean perfectFPSMode;

	/** Execute Thread.yield() during Perfect FPS mode */
	protected boolean perfectYield;

	/** Show player input */
	protected boolean showInput;

	/**
	 * Constructor
	 */
	public StateConfigGeneralSDL() {
		cursor = 0;
		loadConfig(NullpoMinoSDL.propConfig);
	}

	/**
	 * Load settings
	 * @param prop Property file to read from
	 */
	protected void loadConfig(CustomProperties prop) {
		fullscreen = prop.getProperty("option.fullscreen", false);
		se = prop.getProperty("option.se", true);
		bgm = prop.getProperty("option.bgm", false);
		bgmpreload = prop.getProperty("option.bgmpreload", false);
		showbg = prop.getProperty("option.showbg", true);
		showfps = prop.getProperty("option.showfps", true);
		enableframestep = prop.getProperty("option.enableframestep", false);
		maxfps = prop.getProperty("option.maxfps", 60);
		showlineeffect = prop.getProperty("option.showlineeffect", true);
		lineeffectspeed = prop.getProperty("option.lineeffectspeed", 0);
		soundbuffer = prop.getProperty("option.soundbuffer", 1024);
		heavyeffect = prop.getProperty("option.heavyeffect", false);
		fieldbgbright = prop.getProperty("option.fieldbgbright", 128);
		showfieldbggrid = prop.getProperty("option.showfieldbggrid", true);
		darknextarea = prop.getProperty("option.darknextarea", true);
		sevolume = prop.getProperty("option.sevolume", 128);
		bgmvolume = prop.getProperty("option.bgmvolume", 128);
		soundChannels = prop.getProperty("option.soundChannels", 15);
		showmeter = prop.getProperty("option.showmeter", true);
		nextshadow = prop.getProperty("option.nextshadow", false);
		outlineghost = prop.getProperty("option.outlineghost", false);
		perfectFPSMode = prop.getProperty("option.perfectFPSMode", false);
		perfectYield = prop.getProperty("option.perfectYield", false);
		showInput = prop.getProperty("option.showInput", false);
		nexttype = 0;
		if((prop.getProperty("option.sidenext", false) == true) && (prop.getProperty("option.bigsidenext", false) == false)) {
			nexttype = 1;
		} else if((prop.getProperty("option.sidenext", false) == true) && (prop.getProperty("option.bigsidenext", false) == true)) {
			nexttype = 2;
		}
	}

	/**
	 * Save settings
	 * @param prop Property file to save to
	 */
	protected void saveConfig(CustomProperties prop) {
		prop.setProperty("option.fullscreen", fullscreen);
		prop.setProperty("option.se", se);
		prop.setProperty("option.bgm", bgm);
		prop.setProperty("option.bgmpreload", bgmpreload);
		prop.setProperty("option.showbg", showbg);
		prop.setProperty("option.showfps", showfps);
		prop.setProperty("option.enableframestep", enableframestep);
		prop.setProperty("option.maxfps", maxfps);
		prop.setProperty("option.showlineeffect", showlineeffect);
		prop.setProperty("option.lineeffectspeed", lineeffectspeed);
		prop.setProperty("option.soundbuffer", soundbuffer);
		prop.setProperty("option.heavyeffect", heavyeffect);
		prop.setProperty("option.fieldbgbright", fieldbgbright);
		prop.setProperty("option.showfieldbggrid", showfieldbggrid);
		prop.setProperty("option.darknextarea", darknextarea);
		prop.setProperty("option.sevolume", sevolume);
		prop.setProperty("option.bgmvolume", bgmvolume);
		prop.setProperty("option.soundChannels", soundChannels);
		prop.setProperty("option.showmeter", showmeter);
		prop.setProperty("option.nextshadow", nextshadow);
		prop.setProperty("option.outlineghost", outlineghost);
		prop.setProperty("option.perfectFPSMode", perfectFPSMode);
		prop.setProperty("option.perfectYield", perfectYield);
		prop.setProperty("option.showInput", showInput);
		if(nexttype == 0) {
			prop.setProperty("option.sidenext", false);
			prop.setProperty("option.bigsidenext", false);
		} else if(nexttype == 1) {
			prop.setProperty("option.sidenext", true);
			prop.setProperty("option.bigsidenext", false);
		} else if(nexttype == 2) {
			prop.setProperty("option.sidenext", true);
			prop.setProperty("option.bigsidenext", true);
		}
	}

	/*
	 * Draw the game screen
	 */
	@Override
	public void render(SDLSurface screen) throws SDLException {
		// Background
		ResourceHolderSDL.imgMenu.blitSurface(screen);

		// Basic Options
		if(cursor < 17) {
			NormalFontSDL.printFontGrid(1, 1, "GENERAL OPTIONS: BASIC (1/3)", NormalFontSDL.COLOR_ORANGE);
			NormalFontSDL.printFontGrid(1, 3 + cursor, "b", NormalFontSDL.COLOR_RED);

			NormalFontSDL.printFontGrid(2,  3, "SE:" + GeneralUtil.getOorX(se), (cursor == 0));
			NormalFontSDL.printFontGrid(2,  4, "BGM:" + GeneralUtil.getOorX(bgm), (cursor == 1));
			NormalFontSDL.printFontGrid(2,  5, "BGM PRELOAD:" + GeneralUtil.getOorX(bgmpreload), (cursor == 2));
			NormalFontSDL.printFontGrid(2,  6, "SE VOLUME:" + sevolume + "(" + (sevolume * 100 / 128) + "%)", (cursor == 3));
			NormalFontSDL.printFontGrid(2,  7, "BGM VOLUME:" + bgmvolume + "(" + (bgmvolume * 100 / 128) + "%)", (cursor == 4));
			NormalFontSDL.printFontGrid(2,  8, "SHOW BACKGROUND:" + GeneralUtil.getOorX(showbg), (cursor == 5));
			NormalFontSDL.printFontGrid(2,  9, "USE BACKGROUND FADE:" + GeneralUtil.getOorX(heavyeffect), (cursor == 6));
			NormalFontSDL.printFontGrid(2, 10, "SHOW LINE EFFECT:" + GeneralUtil.getOorX(showlineeffect), (cursor == 7));
			NormalFontSDL.printFontGrid(2, 11, "LINE EFFECT SPEED:" + "X " + (lineeffectspeed+1), (cursor == 8));
			NormalFontSDL.printFontGrid(2, 12, "SHOW METER:" + GeneralUtil.getOorX(showmeter), (cursor == 9));
			NormalFontSDL.printFontGrid(2, 13, "DARK NEXT AREA:" + GeneralUtil.getOorX(darknextarea), (cursor == 10));
			NormalFontSDL.printFontGrid(2, 14, "SHOW NEXT ABOVE SHADOW:" + GeneralUtil.getOorX(nextshadow), (cursor == 11));
			NormalFontSDL.printFontGrid(2, 15, "NEXT DISPLAY TYPE:" + NEXTTYPE_OPTIONS[nexttype], (cursor == 12));
			NormalFontSDL.printFontGrid(2, 16, "OUTLINE GHOST PIECE:" + GeneralUtil.getOorX(outlineghost), (cursor == 13));
			NormalFontSDL.printFontGrid(2, 17, "FIELD BG BRIGHT:" + fieldbgbright + "(" + (fieldbgbright * 100 / 255) + "%)", (cursor == 14));
			NormalFontSDL.printFontGrid(2, 18, "SHOW FIELD BG GRID:" + GeneralUtil.getOorX(showfieldbggrid), (cursor == 15));
			NormalFontSDL.printFontGrid(2, 19, "SHOW CONTROLLER INPUT:" + GeneralUtil.getOorX(showInput), (cursor == 16));
		}
		// Advanced Options
		else if(cursor < 23) {
			NormalFontSDL.printFontGrid(1, 1, "GENERAL OPTIONS: ADVANCED (2/3)", NormalFontSDL.COLOR_ORANGE);
			NormalFontSDL.printFontGrid(1, 3 + (cursor - 17), "b", NormalFontSDL.COLOR_RED);

			NormalFontSDL.printFontGrid(2,  3, "FULLSCREEN:" + GeneralUtil.getOorX(fullscreen), (cursor == 17));
			NormalFontSDL.printFontGrid(2,  4, "SHOW FPS:" + GeneralUtil.getOorX(showfps), (cursor == 18));
			NormalFontSDL.printFontGrid(2,  5, "MAX FPS:" + maxfps, (cursor == 19));
			NormalFontSDL.printFontGrid(2,  6, "FRAME STEP:" + GeneralUtil.getOorX(enableframestep), (cursor == 20));
			NormalFontSDL.printFontGrid(2,  7, "FPS PERFECT MODE:" + GeneralUtil.getOorX(perfectFPSMode), (cursor == 21));
			NormalFontSDL.printFontGrid(2,  8, "FPS PERFECT YIELD:" + GeneralUtil.getOorX(perfectYield), (cursor == 22));
		}
		// SDL Options
		else {
			NormalFontSDL.printFontGrid(1, 1, "GENERAL OPTIONS: SDL (3/3)", NormalFontSDL.COLOR_ORANGE);
			NormalFontSDL.printFontGrid(1, 3 + (cursor - 23), "b", NormalFontSDL.COLOR_RED);

			NormalFontSDL.printFontGrid(2,  3, "SOUND BUFFER SIZE:" + soundbuffer, (cursor == 23));
			NormalFontSDL.printFontGrid(2,  4, "MAX SOUND CHANNELS:" + soundChannels, (cursor == 24));
		}

		if((cursor >= 0) && (cursor < UI_TEXT.length)) NormalFontSDL.printTTFFont(16, 432, NullpoMinoSDL.getUIText(UI_TEXT[cursor]));
	}

	/*
	 * Update game state
	 */
	@Override
	public void update() throws SDLException {
		// Cursor movement
		if(GameKeySDL.gamekey[0].isMenuRepeatKey(GameKeySDL.BUTTON_UP)) {
			cursor--;
			if(cursor < 0) cursor = 24;
			ResourceHolderSDL.soundManager.play("cursor");
		}
		if(GameKeySDL.gamekey[0].isMenuRepeatKey(GameKeySDL.BUTTON_DOWN)) {
			cursor++;
			if(cursor > 24) cursor = 0;
			ResourceHolderSDL.soundManager.play("cursor");
		}

		// Configuration changes
		int change = 0;
		if(GameKeySDL.gamekey[0].isMenuRepeatKey(GameKeySDL.BUTTON_LEFT)) change = -1;
		if(GameKeySDL.gamekey[0].isMenuRepeatKey(GameKeySDL.BUTTON_RIGHT)) change = 1;

		if(change != 0) {
			ResourceHolderSDL.soundManager.play("change");

			switch(cursor) {
			case 0:
				se = !se;
				break;
			case 1:
				bgm = !bgm;
				break;
			case 2:
				bgmpreload = !bgmpreload;
				break;
			case 3:
				sevolume += change;
				if(sevolume < 0) sevolume = 128;
				if(sevolume > 128) sevolume = 0;
				break;
			case 4:
				bgmvolume += change;
				if(bgmvolume < 0) bgmvolume = 128;
				if(bgmvolume > 128) bgmvolume = 0;
				break;
			case 5:
				showbg = !showbg;
				break;
			case 6:
				heavyeffect = !heavyeffect;
				break;
			case 7:
				showlineeffect = !showlineeffect;
				break;
			case 8:
				lineeffectspeed += change;
				if(lineeffectspeed < 0) lineeffectspeed = 9;
				if(lineeffectspeed > 9) lineeffectspeed = 0;
				break;
			case 9:
				showmeter = !showmeter;
				break;
			case 10:
				darknextarea = !darknextarea;
				break;
			case 11:
				nextshadow = !nextshadow;
				break;
			case 12:
				nexttype += change;
				if(nexttype < 0) nexttype = 2;
				if(nexttype > 2) nexttype = 0;
				break;
			case 13:
				outlineghost = !outlineghost;
				break;
			case 14:
				fieldbgbright += change;
				if(fieldbgbright < 0) fieldbgbright = 255;
				if(fieldbgbright > 255) fieldbgbright = 0;
				break;
			case 15:
				showfieldbggrid = !showfieldbggrid;
				break;
			case 16:
				showInput = !showInput;
				break;
			case 17:
				fullscreen = !fullscreen;
				break;
			case 18:
				showfps = !showfps;
				break;
			case 19:
				maxfps += change;
				if(maxfps < 0) maxfps = 99;
				if(maxfps > 99) maxfps = 0;
				break;
			case 20:
				enableframestep = !enableframestep;
				break;
			case 21:
				perfectFPSMode = !perfectFPSMode;
				break;
			case 22:
				perfectYield = !perfectYield;
				break;
			case 23:
				soundbuffer += change * 256;
				if(soundbuffer < 0) soundbuffer = 65535;
				if(soundbuffer > 65535) soundbuffer = 0;
				break;
			case 24:
				soundChannels += change;
				if(soundChannels < 0) soundChannels = 50;
				if(soundChannels > 50) soundChannels = 0;
				break;
			}
		}

		// Confirm button
		if(GameKeySDL.gamekey[0].isPushKey(GameKeySDL.BUTTON_A)) {
			ResourceHolderSDL.soundManager.play("decide");

			saveConfig(NullpoMinoSDL.propConfig);
			NullpoMinoSDL.saveConfig();

			NullpoMinoSDL.showfps = showfps;
			NullpoMinoSDL.maxFPS = maxfps;
			NullpoMinoSDL.perfectFPSMode = perfectFPSMode;
			NullpoMinoSDL.perfectYield = perfectYield;

			ResourceHolderSDL.soundManager.changeVolume(sevolume);
			if(showlineeffect) ResourceHolderSDL.loadLineClearEffectImages();
			if(showbg) ResourceHolderSDL.loadBackgroundImages();

			NullpoMinoSDL.enterState(NullpoMinoSDL.STATE_CONFIG_MAINMENU);
		}

		// Cancel button
		if(GameKeySDL.gamekey[0].isPushKey(GameKeySDL.BUTTON_B)) {
			loadConfig(NullpoMinoSDL.propConfig);
			NullpoMinoSDL.enterState(NullpoMinoSDL.STATE_CONFIG_MAINMENU);
		}
	}
}
