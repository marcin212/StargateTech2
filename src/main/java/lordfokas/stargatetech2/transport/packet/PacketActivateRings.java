package lordfokas.stargatetech2.transport.packet;

import lordfokas.stargatetech2.core.base.BasePacket;
import lordfokas.stargatetech2.core.base.BasePacket.ClientToServer;
import lordfokas.stargatetech2.core.packet.PacketCoordinates;
import lordfokas.stargatetech2.transport.tileentity.TileTransportRing;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;

@ClientToServer
public class PacketActivateRings extends PacketCoordinates {
	public boolean up;

	@Override
	protected void writeData() throws Exception {
		output.writeBoolean(up);
	}

	@Override
	protected BasePacket readData(EntityPlayerMP player, Side side) throws Exception {
		up = input.readBoolean();
		TileEntity te = player.worldObj.getTileEntity(x, y, z);
		if(te instanceof TileTransportRing){
			((TileTransportRing)te).teleport(up, 1);
		}
		return null;
	}
}