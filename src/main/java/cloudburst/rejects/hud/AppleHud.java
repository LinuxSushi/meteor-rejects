package cloudburst.rejects.hud;

import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.HudRenderer;
import minegame159.meteorclient.systems.modules.render.hud.modules.HudElement;
import minegame159.meteorclient.utils.player.InvUtils;
import minegame159.meteorclient.utils.render.RenderUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class AppleHud extends HudElement {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Double> scale = sgGeneral.add(new DoubleSetting.Builder()
            .name("scale")
            .description("Scale of golden apple counter.")
            .defaultValue(3)
            .min(1)
            .sliderMin(1)
            .sliderMax(4)
            .build()
    );

    public AppleHud(HUD hud) {
        super(hud, "apples", "Displays the amount of golden apples in your inventory.", false);
    }

    @Override
    public void update(HudRenderer renderer) {
        box.setSize(16 * scale.get(), 16 * scale.get());
    }

    @Override
    public void render(HudRenderer renderer) {
        double x = box.getX();
        double y = box.getY();

        if (isInEditor()) {
            RenderUtils.drawItem(Items.GOLDEN_APPLE.getDefaultStack(), (int) x, (int) y, scale.get(), true);
        } else if (InvUtils.findItemWithCount(Items.GOLDEN_APPLE).count > 0) {
            RenderUtils.drawItem(new ItemStack(Items.GOLDEN_APPLE, InvUtils.findItemWithCount(Items.GOLDEN_APPLE).count), (int) x, (int) y, scale.get(), true);
        }
    }
} 
