package pl.asie.computronics.integration.fsp;

import pl.asie.computronics.integration.ManagedEnvironmentOCTile;
import flaxbeard.steamcraft.api.ISteamTransporter;
import li.cil.oc.api.Network;
import li.cil.oc.api.network.Arguments;
import li.cil.oc.api.network.Callback;
import li.cil.oc.api.network.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.DriverTileEntity;
import net.minecraft.world.World;

public class DriverSteamTransporter extends DriverTileEntity {
	public class ManagedEnvironmentST extends ManagedEnvironmentOCTile<ISteamTransporter> {
		public ManagedEnvironmentST(ISteamTransporter tile, String name) {
			super(tile, name);
		}

		@Callback(direct = true)
		public Object[] getSteamPressure(Context c, Arguments a) {
			return new Object[]{tile.getPressure()};
		}
		
		@Callback(direct = true)
		public Object[] getSteamCapacity(Context c, Arguments a) {
			return new Object[]{tile.getCapacity()};
		}
		
		@Callback(direct = true)
		public Object[] getSteamAmount(Context c, Arguments a) {
			return new Object[]{tile.getSteam()};
		}
	}
	
	@Override
	public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
		return new ManagedEnvironmentST((ISteamTransporter)world.getTileEntity(x, y, z), "steam_transporter");
	}

	@Override
	public Class<?> getTileEntityClass() {
		return ISteamTransporter.class;
	}
}
