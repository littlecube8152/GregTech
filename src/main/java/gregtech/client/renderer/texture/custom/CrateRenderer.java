package gregtech.client.renderer.texture.custom;

import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.texture.IconRegistrar;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.ColourMultiplier;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

public class CrateRenderer implements IconRegistrar {

    private final String basePath;

    @SideOnly(Side.CLIENT)
    private TextureAtlasSprite sideSprite;

    public CrateRenderer(String basePath) {
        this.basePath = basePath;
        Textures.iconRegisters.add(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(@NotNull TextureMap textureMap) {
        this.sideSprite = textureMap.registerSprite(GTUtility.gregtechId("blocks/" + basePath));
    }

    public void render(CCRenderState renderState, Matrix4 translation, int baseColor, IVertexOperation[] pipeline) {
        IVertexOperation[] basePipeline = ArrayUtils.add(pipeline, new ColourMultiplier(baseColor));

        for (EnumFacing renderSide : EnumFacing.VALUES) {
            Textures.renderFace(renderState, translation, basePipeline, renderSide, Cuboid6.full, sideSprite,
                    BlockRenderLayer.CUTOUT_MIPPED);
        }
    }

    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getParticleTexture() {
        return sideSprite;
    }
}
