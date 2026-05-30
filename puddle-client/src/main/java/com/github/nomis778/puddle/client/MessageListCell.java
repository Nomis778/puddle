package com.github.nomis778.puddle.client;

import com.github.nomis778.puddle.client.chat.MessageResponse;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;

public class MessageListCell extends ListCell<MessageResponse> {
    @Override
    public void updateItem(MessageResponse msg, boolean empty) {
        super.updateItem(msg, empty);
        if(empty || msg == null) {
            setGraphic(null);
            return;
        }

        HBox row = new HBox(10);

        LocalDateTime timeStamp = msg.getTimeStamp();
        if(timeStamp != null) {
            Label timeLabel = new Label("[%02d:%02d]".formatted(timeStamp.getHour(), timeStamp.getMinute()));
            row.getChildren().add(timeLabel);
        }

        PublicUser sender = msg.getSender();
        if(sender != null) {
            Label senderLabel = new Label(sender.username() + ":");
            row.getChildren().add(senderLabel);
        }

        Label content = new Label(msg.getContent());
        row.getChildren().add(content);
        setGraphic(row);
    }
}
