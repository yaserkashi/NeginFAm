package com.Baloot.util;

import java.awt.Color;
import java.util.Random;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.BackgroundProducer;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.producer.TextProducer;

public class CaptchaUtil {
        
	public static Captcha generateNewCaptcha() {
		Captcha captcha = null;
		Random random = new Random();
		int captchaLength = 4 + random.nextInt(3);
		char[] captchaChars = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
//			{ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
//				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		TextProducer textProducer = new DefaultTextProducer(captchaLength, captchaChars);
		int colorOffset = 120;
		Color fromColor = new Color(colorOffset + random.nextInt(256 - colorOffset), colorOffset
				+ random.nextInt(256 - colorOffset), colorOffset + random.nextInt(256 - colorOffset));
		Color toColor = new Color(colorOffset + random.nextInt(256 - colorOffset), colorOffset
				+ random.nextInt(256 - colorOffset), colorOffset + random.nextInt(256 - colorOffset));
		BackgroundProducer backgroundProducer = new GradiatedBackgroundProducer(fromColor, toColor);
		int numberOfNoises = 2 + random.nextInt(2);
		switch (numberOfNoises) {
		case 2:
			captcha = new Captcha.Builder(150, 50).addText(textProducer).addNoise(new CurvedLineNoiseProducer())
					.addNoise(new CurvedLineNoiseProducer()).addBackground(backgroundProducer).build();
			break;
		case 3:
			captcha = new Captcha.Builder(150, 50).addText(textProducer).addNoise(new CurvedLineNoiseProducer())
					.addNoise(new CurvedLineNoiseProducer()).addNoise(new CurvedLineNoiseProducer())
					.addBackground(backgroundProducer).build();
			break;

		}
		return captcha;
	}
}
