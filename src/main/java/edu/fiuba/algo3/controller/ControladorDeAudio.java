package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;


public class ControladorDeAudio {

    private static ControladorDeAudio controladorAudio;
    private List<MediaPlayer> reproductores = new ArrayList<>();
    private MediaPlayer repActual;
    private boolean reproduciendo;

    private ControladorDeAudio() {
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/Escalation.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/ASongForTheChildrenOfWW2.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/OperationBarbarossa.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/Aggression.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/GeneralWarBringForthTheTanks.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/OperationCompass.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/AlliesPeaceMorningOfD-day.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/GeneralPeaceHeartsOfMen.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/Retribution.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/AlliesWarTheRoyalAirForce.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/GeneralWarTheAttack.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/SovietVictory.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/AxisPeaceKrakow.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/HeavyWater.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/TheGreatPatrioticWar.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/AxisTheme.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/HeroesOfElAlamein.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/TheMightOfSovietUnion.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/ClaustrophobicMarch.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/Hoi4Main.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/TheRedArmy.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/CominternTheme.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/LondonInFlames.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/TheWarEnds.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/CommunistAmerica.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/LuftwaffeMarchReprise.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/WarAxis1DaysOfThunder.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/EndOfTheTour.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/MotherRussia.mp3").toString())));
        reproductores.add(new MediaPlayer(new Media(App.class.getResource("/musica/WeAreSoldiers.mp3").toString())));



        for (int i = 0; i < reproductores.size(); i++) {
            final MediaPlayer player     = reproductores.get(i);
            final MediaPlayer nextPlayer = reproductores.get((i + 1) % reproductores.size());
            player.setOnEndOfMedia(new Runnable() {
                @Override public void run() {
                    nextPlayer.play();
                    repActual = nextPlayer;
                }
            });
        }

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

    public void play(){
        if(reproduciendo){
            repActual.stop();
            reproduciendo = false;
        }else{
            repActual.play();
            reproduciendo = true;
        }
    }


    public void skip(){
        if(reproduciendo){
            repActual.stop();
        }
        repActual = reproductores.get((reproductores.indexOf(repActual)+1)%reproductores.size());
        repActual.play();
        reproduciendo = true;
    }
}
