package me.Danker.commands;


import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

import me.Danker.features.inGameGui.renderGuiScreen;





public class DrawEntityCommand extends CommandBase{

        @Override
        public String getCommandName() {
            return "rendertest";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/" + getCommandName();
        }

        public static String usage(ICommandSender arg0) {
            return new me.Danker.commands.DrawEntityCommand().getCommandUsage(arg0);
        }

        @Override
        public int getRequiredPermissionLevel() {
            return 0;
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) throws CommandException {
            new renderGuiScreen().render("YETI");
        }
    }



