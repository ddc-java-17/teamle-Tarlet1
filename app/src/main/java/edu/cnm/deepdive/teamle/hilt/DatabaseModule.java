package edu.cnm.deepdive.teamle.hilt;

import android.content.Context;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.teamle.model.dao.GameResultDao;
import edu.cnm.deepdive.teamle.model.dao.UserDao;
import edu.cnm.deepdive.teamle.service.TeamleDatabase;
import javax.inject.Singleton;

/**
 * Uses Dagger {@link Provides @Provides}-annotated methods to satisfy dependencies on concrete
 * implementations of {@link TeamleDatabase} and {@link UserDao}.
 */
@InstallIn(SingletonComponent.class)
@Module
public final class DatabaseModule {

  DatabaseModule() {
    // Package-private constructor to avoid automatic HTML documentation generation.
  }

  @Provides
  @Singleton
  TeamleDatabase provideTeamleDatabase(@ApplicationContext Context context) {
    return Room
        .databaseBuilder(context, TeamleDatabase.class, TeamleDatabase.getName())
        .addCallback(new TeamleDatabase.Callback())
        .build();
  }

  @Provides
  @Singleton
  UserDao provideUserDao(TeamleDatabase database) {
    return database.getUserDao();
  }

  @Provides
  @Singleton
  GameResultDao provideResultDao(TeamleDatabase database) {
    return database.getGameResultDao();
  }

}
