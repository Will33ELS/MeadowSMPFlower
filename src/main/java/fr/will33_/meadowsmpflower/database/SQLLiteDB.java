package fr.will33_.meadowsmpflower.database;

import com.google.common.base.Preconditions;
import fr.will33_.meadowsmpflower.MeadowSmpFlower;
import fr.will33_.meadowsmpflower.exception.MeadowLoadPlayerDataException;
import fr.will33_.meadowsmpflower.model.MEffect;
import fr.will33_.meadowsmpflower.model.MPlayer;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Optional;

public class SQLLiteDB {

    private final Path sqlFile = Paths.get(MeadowSmpFlower.getInstance().getDataFolder().toString(), "database.db");
    private Connection connection;

    public SQLLiteDB(){
        try{
            if(!Files.exists(this.sqlFile))
                Files.createFile(this.sqlFile);
        }catch (IOException err){
            err.printStackTrace();
        }
        this.setup();
    }

    /**
     * Connexion à la base de données puis création des tables si elles n'existent pas
     */
    private void setup(){
        if(!this.isConnected()){
            try{
                this.connection = DriverManager.getConnection("jdbc:sqlite:" + sqlFile.toAbsolutePath());

                //CREATE players TABLE
                PreparedStatement playerStatement = this.connection.prepareStatement(
                        "CREATE TABLE IF NOT EXISTS `players` " +
                                "(uuid VARCHAR(255) PRIMARY KEY, effect VARCHAR(100))"
                );
                playerStatement.executeUpdate();
                playerStatement.close();

            }catch (SQLException err){
                err.printStackTrace();
            }
        }
    }

    /**
     * Vérification de la connexion
     * @return
     */
    private boolean isConnected() {
        try {
            return (this.connection != null) && (!this.connection.isClosed()) && this.connection.isValid(1000);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Fermer la connexion
     * @throws Exception
     */
    private void shutdownDataSource() throws Exception{
        try {
            if(this.connection != null)
                this.connection.close();
        } catch (SQLException e) {
            throw new Exception("Erreur: "+e.getMessage());
        }
    }

    /**
     * Récupérer la connexion
     * @return
     */
    public Connection getDatabase() {
        if (!this.isConnected()) {
            try {
                this.shutdownDataSource();
            }catch (Exception err){
                err.printStackTrace();
            }
            this.setup();
        }
        return this.connection;
    }

    /**
     * Load player data
     * @param player Instance of the player
     * @return Instance of the player
     * @throws MeadowLoadPlayerDataException
     */
    public MPlayer loadPlayerData(Player player) throws MeadowLoadPlayerDataException {
        MPlayer mPlayer = new MPlayer(player.getUniqueId());
        try{
            PreparedStatement preparedStatement = this.getDatabase().prepareStatement("SELECT effect FROM players WHERE uuid = ?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Optional.ofNullable(MeadowSmpFlower.getInstance().getEffectByName(resultSet.getString("effect")))
                                .ifPresent(mPlayer::setEffect);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException err){
            throw new MeadowLoadPlayerDataException(err.getMessage());
        }
        return mPlayer;
    }

    /**
     * Save player data (Requirement: Player must have selected an effect)
     * @param mPlayer Instance of the player
     */
    public void savePlayerData(MPlayer mPlayer){
        Preconditions.checkNotNull(mPlayer.getEffect());
        try{
            PreparedStatement preparedStatement = this.getDatabase().prepareStatement("INSERT INTO players (uuid, effect) VALUES (?, ?) ON CONFLICT DO UPDATE SET effect = ?");
            preparedStatement.setString(1, mPlayer.getUUID().toString());
            preparedStatement.setString(2, mPlayer.getEffect().getName());
            preparedStatement.setString(3, mPlayer.getEffect().getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException err){
            err.printStackTrace();
        }
    }

    /**
     * Check if effect is already selected
     * @param effect Instance of the effect
     * @return
     */
    public boolean isAlreadySelected(MEffect effect){
        boolean alreadySelected = false;
        try{
            PreparedStatement preparedStatement = this.getDatabase().prepareStatement("SELECT FROM players WHERE effect = ?");
            preparedStatement.setString(1, effect.getName());
            ResultSet resultSet = preparedStatement.getResultSet();
            alreadySelected = resultSet.next();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException err){
            err.printStackTrace();
        }
        return alreadySelected;
    }
}
