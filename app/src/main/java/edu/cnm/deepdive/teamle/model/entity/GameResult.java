package edu.cnm.deepdive.teamle.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.time.Duration;
import java.time.Instant;

@Entity(
    tableName = "game_result",
    indices = @Index(value = {"guess_count", "duration"}),
    foreignKeys = @ForeignKey(entity = User.class, parentColumns = "user_id", childColumns = "user_id",
        onDelete = ForeignKey.CASCADE)
)

public class GameResult {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "game_result_id")
  private long id;

  @NonNull
  @ColumnInfo(index = true)
  private Instant timestamp = Instant.now();

  @ColumnInfo(name = "size", index = true)
  private int size;

  @ColumnInfo(name = "guess_count", index = true)
  private int guessCount;

  @NonNull
  @ColumnInfo(index = true)
  private Duration duration = Duration.ZERO;

  @ColumnInfo(name = "user_id", index = true)
  private Long userId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@NonNull Instant timestamp) {
    this.timestamp = timestamp;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public int getGuessCount() {
    return guessCount;
  }

  public void setGuessCount(int guessCount) {
    this.guessCount = guessCount;
  }

  @NonNull
  public Duration getDuration() {
    return duration;
  }

  public void setDuration(@NonNull Duration duration) {
    this.duration = duration;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
