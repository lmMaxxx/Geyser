/*
 * Copyright (c) 2019-2022 GeyserMC. http://geysermc.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author GeyserMC
 * @link https://github.com/GeyserMC/Geyser
 */

package org.geysermc.geyser.registry.type;

import com.nukkitx.nbt.NbtList;
import com.nukkitx.nbt.NbtMap;
import com.nukkitx.protocol.bedrock.data.BlockPropertyData;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import lombok.Builder;
import lombok.Value;
import org.geysermc.geyser.api.block.custom.CustomBlockState;

import java.util.List;
import java.util.Map;

@Builder
@Value
public class BlockMappings {
    int bedrockAirId;
    int bedrockWaterId;
    int bedrockMovingBlockId;

    int blockStateVersion;

    int[] javaToBedrockBlocks;
    int[] javaToVanillaBedrockBlocks;

    NbtList<NbtMap> bedrockBlockStates;
    int[] remappedVanillaIds;

    int commandBlockRuntimeId;

    Object2IntMap<NbtMap> itemFrames;
    Map<String, NbtMap> flowerPotBlocks;

    IntSet jigsawStateIds;

    List<BlockPropertyData> blockProperties;
    Object2IntMap<CustomBlockState> customBlockStateIds;

    public int getBedrockBlockId(int state) {
        if (state >= this.javaToBedrockBlocks.length) {
            return bedrockAirId;
        }
        return this.javaToBedrockBlocks[state];
    }

    public int getVanillaBedrockBlockId(int state) {
        if (state >= this.javaToVanillaBedrockBlocks.length) {
            return bedrockAirId;
        }
        return this.javaToVanillaBedrockBlocks[state];
    }

    public int getItemFrame(NbtMap tag) {
        return this.itemFrames.getOrDefault(tag, -1);
    }

    public boolean isItemFrame(int bedrockBlockRuntimeId) {
        return this.itemFrames.values().contains(bedrockBlockRuntimeId);
    }
}