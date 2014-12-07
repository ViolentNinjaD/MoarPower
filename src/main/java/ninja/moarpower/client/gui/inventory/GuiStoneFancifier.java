package ninja.moarpower.client.gui.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import ninja.moarpower.Reference;
import ninja.moarpower.Textures;
import ninja.moarpower.inventory.ContainerStoneFancifier;
import ninja.moarpower.tile.TileStoneFancifier;
import org.lwjgl.opengl.GL11;

/** @author ViolentNinjaD
    LGPLv3
**/

@SideOnly(Side.CLIENT)
public class GuiStoneFancifier extends GuiContainer
{
    private TileStoneFancifier tileFancifier;
    public GuiStoneFancifier(InventoryPlayer player, TileStoneFancifier tileFancifier)
    {
        super(new ContainerStoneFancifier(player, tileFancifier));
        ySize = 176;
        this.tileFancifier = tileFancifier;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = tileFancifier.isInvNameLocalized() ? tileFancifier.getInvName() : StatCollector.translateToLocal(tileFancifier.getInvName());
        fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Reference.CONTAINER_INVENTORY), 8, ySize - 96 + 2, 4210752);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_FANCIFIER);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
