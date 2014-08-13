package lordfokas.stargatetech2.core.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import lordfokas.stargatetech2.core.base.BasePacket;
import lordfokas.stargatetech2.core.base.BasePacket.ClientToServer;
import lordfokas.stargatetech2.core.machine.Face;
import lordfokas.stargatetech2.core.machine.TileMachine;
import cpw.mods.fml.relauncher.Side;

@ClientToServer
public class PacketToggleMachineFace extends PacketCoordinates {
	public Face face;
	
	@Override
	protected void writeData() throws Exception {
		output.writeInt(face.ordinal());
	}

	@Override
	protected BasePacket readData(EntityPlayerMP player, Side side) throws Exception {
		face = Face.values()[input.readInt()];
		TileEntity te = player.worldObj.getTileEntity(x, y, z);
		if(te instanceof TileMachine){
			((TileMachine)te).toggleFace(face);
		}
		return null;
	}
}