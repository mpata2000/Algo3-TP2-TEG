package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ControladorDeAudio {

    private static ControladorDeAudio controladorAudio;
    private final List<MediaPlayer> reproductores = new ArrayList<>();
    private MediaPlayer repActual;
    private boolean reproduciendo;

    private ControladorDeAudio() {
        /* Creo los Media player con la musica ya seteada, ignorar lo feas que son las lineas */
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/Escalation.mp3"                  )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/ASongForTheChildrenOfWW2.mp3"    )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/OperationBarbarossa.mp3"         )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/Aggression.mp3"                  )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/GeneralWarBringForthTheTanks.mp3")).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/OperationCompass.mp3"            )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/AlliesPeaceMorningOfD-day.mp3"   )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/GeneralPeaceHeartsOfMen.mp3"     )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/Retribution.mp3"                 )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/AlliesWarTheRoyalAirForce.mp3"   )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/GeneralWarTheAttack.mp3"         )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/SovietVictory.mp3"               )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/AxisPeaceKrakow.mp3"             )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/HeavyWater.mp3"                  )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/TheGreatPatrioticWar.mp3"        )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/AxisTheme.mp3"                   )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/HeroesOfElAlamein.mp3"           )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/TheMightOfSovietUnion.mp3"       )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/ClaustrophobicMarch.mp3"         )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/Hoi4Main.mp3"                    )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/TheRedArmy.mp3"                  )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/CominternTheme.mp3"              )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/LondonInFlames.mp3"              )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/TheWarEnds.mp3"                  )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/CommunistAmerica.mp3"            )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/LuftwaffeMarchReprise.mp3"       )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/WarAxis1DaysOfThunder.mp3"       )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/EndOfTheTour.mp3"                )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/MotherRussia.mp3"                )).toString())));
        reproductores.add(new MediaPlayer(new Media(Objects.requireNonNull(App.class.getResource("/musica/WeAreSoldiers.mp3"               )).toString())));


        /*Le seteo a cada media player la proxima cancion*/
        for (int i = 0; i < reproductores.size(); i++) {
            final MediaPlayer player     = reproductores.get(i);
            final MediaPlayer nextPlayer = reproductores.get((i + 1) % reproductores.size());
            player.setOnEndOfMedia(() -> {
                nextPlayer.play();
                repActual = nextPlayer;
            });
        }

        /* Seteo el reproductor actual y lo pongo a reproducir*/
        repActual = reproductores.get(0);
        repActual.play();
        reproduciendo = true;
    }
    
    public static ControladorDeAudio getInstance(){
        if(controladorAudio == null){
            controladorAudio = new ControladorDeAudio();
        }
        return  controladorAudio;
    }

    /* Reproduce o para la cancion de reproductor actual*/
    public void play(){
        if(reproduciendo){
            repActual.stop();
            reproduciendo = false;
        }else{
            repActual.play();
            reproduciendo = true;
        }
    }

    /* Saltea a la proxima cancion de la play list si llega al final vuelve al principio*/
    public void skip(){
        if(reproduciendo) repActual.stop();

        repActual = reproductores.get((reproductores.indexOf(repActual)+1)%reproductores.size());
        repActual.play();
        reproduciendo = true;
    }

    /* Vuelve para atras una cancion de la play list si llega al principio va al final */
    public void back() {
        if(reproduciendo) repActual.stop();

        int pos = (reproductores.indexOf(repActual)-1);

        if(pos < 0) pos = reproductores.size() - 1;

        repActual = reproductores.get(pos);
        repActual.play();
        reproduciendo = true;
    }
}
