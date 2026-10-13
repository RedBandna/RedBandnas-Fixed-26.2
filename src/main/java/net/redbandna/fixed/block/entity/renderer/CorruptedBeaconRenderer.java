package net.redbandna.fixed.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import net.redbandna.fixed.block.entity.custom.CorruptedBeaconBlockEntity;
import org.joml.Quaternionf;

public class CorruptedBeaconRenderer implements BlockEntityRenderer<CorruptedBeaconBlockEntity, CorruptedBeaconRenderState> {

    static float[][] face = {
            {(float) Math.sqrt(3), 0, 0},
            {(float) Math.sqrt(1.5), (float) Math.sqrt(1.5), 0},
            {1, 1, 1},
            {(float) Math.sqrt(1.5), 0, (float) Math.sqrt(1.5)}
    };

    @Override
    public CorruptedBeaconRenderState createRenderState() {
        return new CorruptedBeaconRenderState();
    }

    @Override
    public void submit(CorruptedBeaconRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        /*poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        Vec3 relative = camera.pos.subtract(Vec3.atCenterOf(state.blockPos));
        double xRot = Math.atan2(relative.x, relative.z);
        double yRot = Math.atan2(-relative.y, relative.horizontalDistance());
        poseStack.mulPose(new Quaternionf().rotationYXZ((float) xRot, (float) yRot, 0.0F));
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.textBackground(), ((lPose, buffer) -> {
            for (int i = 0; i < 90; ) {
                buffer.addVertex(lPose, 0f, 0f, 0f).setColor(0xC8EFBF00).setLight(state.lightCoords);
                buffer.addVertex(lPose, (float) Math.cos(i * Math.PI / 45) * 2, (float) Math.sin(i * Math.PI / 45) * 2, 0f).setColor(0xC8EFBF00).setLight(state.lightCoords);
                buffer.addVertex(lPose, (float) Math.cos(++i * Math.PI / 45) * 2, (float) Math.sin(i * Math.PI / 45) * 2, 0f).setColor(0xC8EFBF00).setLight(state.lightCoords);
                buffer.addVertex(lPose, (float) Math.cos(++i * Math.PI / 45) * 2, (float) Math.sin(i * Math.PI / 45) * 2, 0f).setColor(0xC8EFBF00).setLight(state.lightCoords);
            }
        }));
        poseStack.popPose();*/
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.textBackground(), ((lPose, buffer) -> {
            for (int i = 0; i < 6; ++i) for (int j = 0; j < 4; ++j) for (int k = 0; k < 4; ++k) {
                buffer.addVertex(lPose, pos(i, j, k , 0), pos(i, j, k , 1), pos(i, j, k , 2)).setColor(0xC8EFBF00).setLight(state.lightCoords);
            }
        }));
        poseStack.popPose();
    }

    private float pos(int i, int j, int k, int d) {
        return face[k ^ ((k << 1) & ((j + 1) ^ (i < 3 == ((i + d) % 3 == 0) ? 0 : 2)) & 2)][(i + d) % 3] * (((j << ((i + d + 2) % 3)) & 2) - 1) * (i < 3 ? 1 : -1);


    }
}
