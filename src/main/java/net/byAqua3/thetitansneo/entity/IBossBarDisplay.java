package net.byAqua3.thetitansneo.entity;

import net.minecraft.resources.Identifier;

public interface IBossBarDisplay {
	
	Identifier getBossBarTexture();
	
	public default int getBossBarNameColor() {
		return 16777215;
	}
	
	public default int getBossBarHealthColor() {
		return 16777215;
	}
	
	int getBossBarWidth();
	int getBossBarHeight();
	int getBossBarInterval();
	
	int getBossBarVOffset();
	int getBossBarVHeight();
	
	int getBossBarTextOffset();
	
}
