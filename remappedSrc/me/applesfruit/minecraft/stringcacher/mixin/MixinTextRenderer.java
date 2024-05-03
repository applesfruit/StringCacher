package me.applesfruit.minecraft.stringcacher.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.font.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;

@Mixin(TextRenderer.class)
public class MixinTextRenderer {

    private HashMap<String, Integer> widthMap;

    @Inject(at = @At(value = "HEAD"), method = "init")
    private void MixinTextRenderer(CallbackInfo ci)
    {
        this.widthMap = new HashMap<>();
    }

    @Inject(at = @At(value = "HEAD"), method = "getStringWidth", cancellable = true)
    private void getStringWidth(String s45, CallbackInfoReturnable<Integer> cir)
    {
        if (this.widthMap.containsKey(s45))
        {
            cir.setReturnValue(this.widthMap.get(s45));
        }
    }

    @Inject(at = @At(value = "TAIL"), method = "getStringWidth")
    private void getStringWidthB(String s, CallbackInfoReturnable<Integer> cir, @Local int i)
    {
        this.widthMap.put(s, i);
    }

}
