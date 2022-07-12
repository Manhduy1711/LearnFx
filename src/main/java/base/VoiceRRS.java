package base;

import Controller.HomeController;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

public class VoiceRRS {
    public static void speaker(String word) throws Exception {
        VoiceProvider tts = new VoiceProvider("c7bead93fd2d478d9c2cce966f86183b");
        VoiceParameters parameters = new VoiceParameters(word,  AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        parameters.setBase64(false);
        parameters.setLanguage("ja-jp");
        parameters.setVoice(HomeController.voiceName);
        parameters.setSSML(false);
        parameters.setRate(-2);
        byte[] voice = tts.speech(parameters);
        FileOutputStream fos = new FileOutputStream("C:\\Users\\tedof\\IdeaProjects\\LearnFx\\target\\classes\\audio.wav");
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();
        File file = new File("C:\\Users\\tedof\\IdeaProjects\\LearnFx\\target\\classes\\audio.wav");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
