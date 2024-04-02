package edu.cnm.deepdive.teamle.hilt;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import java.util.Random;

/**
 * Provides and returns a random secret team from selected preferences.
 */
@InstallIn(SingletonComponent.class)
@Module
public class ComponentsModule {

  ComponentsModule() {
  }

  @Provides
  Random provideRNG() {
    return new Random();
  }
}
