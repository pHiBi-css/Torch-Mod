# 🔥 Living Torch Mod - Documentation Complète

## 📖 Table des Matières
1. [Vue d'ensemble](#-vue-densemble)
2. [Entités](#-entités)
3. [Système de Faim](#-système-de-faim)
4. [Biomes](#-adaptation-aux-biomes)
5. [Spawn & Obtention](#-spawn--obtention)
6. [Guide de Jeu](#-guide-de-jeu)
7. [Installation](#-installation)
8. [Structure du Projet](#-structure-du-projet)
9. [Statistiques](#-statistiques)

---

## 🎮 Vue d'ensemble

Le **Living Torch Mod** ajoute à Minecraft un système complet d'entités vivantes basées sur les torches. Vous pouvez spawner une **Torche Vivante** qui vous suivra, nécessite de la nourriture, génère de la lumière dynamique, et change d'état selon sa faim. Il existe aussi deux boss redoutables pour le combat:

- 🔥 **Torch Boss**: Boss intermédiaire (100 PV)
- 🔥👹 **Inferno Torch Boss**: Boss ultime (800 PV)

---

## 🦾 Entités

### 1️⃣ Torche Vivante (Living Torch)

**Type**: Entité neutre de compagnie  
**Dimensions**: 0.5 x 0.9 blocs  
**PV**: 6 (3 ❤️)  
**Portée de suivi**: 32 blocs

#### Caractéristiques:
- ✅ Vous suit automatiquement
- ✅ Génère de la lumière (variable selon l'état)
- ✅ Change d'état selon la faim
- ✅ Fuit l'eau et la pluie
- ✅ S'adapte au biome
- ✅ Peut attaquer les mobs en état EXCITÉ
- ✅ 4 états visuels distincts avec Blockbench

#### Les 4 États:

| État | Faim | Lumière | Mouvement | Actions | Texture |
|------|------|---------|-----------|---------|----------|
| **🌑 Éteinte** | 0% | Aucune (0) | Immobile | Morte - reste sur place | Grise et sombre |
| **🔶 Faible** | 1-30% | Faible (5) | Peu de mouvement | Vulnérable, peu active | Orange sombre |
| **🔥 Normal** | 31-80% | Normal (10) | Normal | Suivi optimal ✨ | Orange vif + rayons |
| **🌟 Excitée** | 81-100% | Brillante (15) | Très actif | Attaque faible + feu | Rouge/jaune + gros rayons |

**Mécanique Excitée**:
- Attaque les mobs proches: 0.5 dégâts
- Met le feu aux mobs (2 ticks)
- Portée d'attaque: 5 blocs
- Tempo d'attaque: 30 ticks de chance

**Drops**:
- 2x Charbon de Bois
- 1x Stick

---

### 2️⃣ Torch Boss

**Type**: Mob hostile/boss  
**Dimensions**: 1.5 x 2.7 blocs (3 blocs de haut)  
**PV**: 100 (50 ❤️)  
**Portée d'attaque**: 8 blocs  
**Portée de suivi**: 32 blocs

#### Caractéristiques:
- ✅ Barre de boss jaune (10 segments)
- ✅ Attaque: 5 dégâts + knockback
- ✅ Met le feu (30% chance)
- ✅ Attaque les mobs proches (3 dégâts)
- ✅ Crée du feu autour (optionnel)
- ✅ Sons de blaze

**Drops**:
- 4x Charbon Personnalisé (+100 faim)
- 2x Charbon classique

**Spawn**: 10% sur torche d'âme (+ 3 éclairs)

---

### 3️⃣ Inferno Torch Boss (Boss Ultime) 🔥👹

**Type**: Mob hostile/boss extrême  
**Dimensions**: 2.0 x 4.0 blocs (4 blocs de haut!)  
**PV**: 800 (400 ❤️) - ÉNORME!  
**Attaque**: 10 dégâts feu  
**Portée d'attaque**: 8 blocs  
**Portée de suivi**: 64 blocs  
**Armor**: 5.0

#### Caractéristiques EXTRÊMES:
- ✅ Barre de boss ROUGE (20 segments)
- ✅ **Toujours enflammé** (FireTicks = MAX)
- ✅ Crée du feu autour continuellement
- ✅ Dégâts augmentés avec armure adverse
- ✅ Knockback puissant
- ✅ Met le feu à l'attaquant (40% chance)
- ✅ Attaque rapide: 40 ticks entre attaques
- ✅ Portée d'attaque large: 8 blocs
- ✅ Sons de blaze intensifiés
- ✅ Noircit le ciel lors du combat

**Mécanique Spéciale**:
- Dégâts de base: 10
- Dégâts supplémentaires = armure × 0.5
- Exemple: Armure 10 = 10 + 5 = 15 dégâts!
- Met le feu: 10 ticks

**Drops Épiques**:
- 8x Charbon Personnalisé (4 × seau de lave!)
- 4x Charbon classique
- 2x Diamants

**Spawn**: 1% sur torche d'âme (+ 5 éclairs TEMPÊTE!)

---

## 🍖 Système de Faim

La **Torche Vivante** a besoin de manger pour survivre. Faim max: **100**

### Aliments:

| Aliment | Faim | Notes |
|---------|------|-------|
| **Charbon Custom** | +100 | 🏆 Drop du Inferno Boss - TRÈS puissant |
| **Charbon de Bois** | +50 | Drop de la torche vivante |
| **Seau de Lave** | +50 | Aliment standard |
| **Charbon** | +25 | Charbon Minecraft normal |
| **Bois** | +10 | Oak, Birch, Spruce Wood |

### Consommation:

```
Consommation Passive:
- -1 faim/tick (normal)
- -2 faim/tick (eau/pluie)
- -1 faim/tick supplément (froid)
- -2 faim/tick supplément (humide)

Réaction à l'eau:
- Fuit l'eau et la pluie
- Saute toutes les 20 ticks pour s'échapper
- Consomme 2× plus en contact avec l'eau
```

### Survie:
- Quand faim = 0: L'entité meurt
- Alimentation préventive recommandée!

---

## 🌍 Adaptation aux Biomes

La torche change de comportement selon le biome:

### Biomes Chauds (Désert, Nether)
```
✅ Efficace
- Consomme MOINS ou RIEN
- État optimal!
- Recommandé pour une torche heureuse
```

### Biomes Froids (Neige, Taïga)
```
⚠️ Difficile
- Consomme +1 faim/tick
- Affaiblie
- Nécessite plus de nourriture
```

### Biomes Humides (Jungle, Marais)
```
❌ Très difficile
- Consomme +2 faim/tick
- Très affaiblie
- À éviter!
```

### Biomes Normaux (Forêt, Plaines)
```
✓ Neutre
- Consommation standard
- Équilibré
```

---

## 🎯 Spawn & Obtention

### Torche Vivante

**Méthode**: Posez une **torche normale** (bloc)

```
Chance de spawn:
- 10% → Torche Vivante + 1 éclair
- 90% → Torche classique (aucun spawn)
```

**Apparence**:
- Animation éclair à la spawn
- Commence avec 50 faim (état NORMAL)
- Suit immédiatement le joueur

---

### Torch Boss (Boss Intermédiaire)

**Méthode**: Posez une **torche d'âme** (Soul Torch)

```
Chance de spawn:
- 10% → Torch Boss + 3 éclairs
```

**Apparence**:
- 3 éclairs visibles
- Barre de boss jaune
- Hauteur: 2.7 blocs
- Commence à 100 PV

---

### Inferno Torch Boss (Boss Ultime) 🔥

**Méthode**: Posez une **torche d'âme** (Soul Torch)

```
Chance de spawn:
- 1% → Inferno Torch Boss + 5 éclairs TEMPÊTE!
```

**Apparence Spectaculaire**:
- 5 éclairs éclaboussés autour
- Barre de boss ROUGE (20 segments)
- Toujours enflammé
- Hauteur: 4 blocs (ÉNORME!)
- Commence à 800 PV
- Créé du feu autour immédiatement
- Noircit le ciel (DarkenSky = true)

---

## 🎮 Guide de Jeu

### 🚀 Démarrage

1. **Trouver une torche classique**
2. **La poser** (utiliser dans l'air)
3. **10% chance** de spawner une torche vivante
4. **Éclair** signale le spawn réussi
5. **La torche vous suit automatiquement!**

### 🍖 Nourrir votre Torche

1. **Miner du charbon** (basique)
2. **Jeter le charbon** près de la torche
3. **Ou utilisez du bois/lave**
4. **Gardez-la nourrie** (état NORMAL optimal)

### 🎯 Combat contre Torch Boss

1. **Trouver une torche d'âme**
2. **La poser** pour 10% chance boss
3. **Le boss spawne** avec 3 éclairs
4. **Combat**: 100 PV, attaque 5 dégâts
5. **Drops**: 4× Charbon Custom
6. **Utilisez le charbon** pour nourrir la torche vivante

### ⚔️ Combat contre Inferno Torch Boss

1. **Trouver une torche d'âme**
2. **La poser** pour 1% chance (très rare)
3. **Le boss spawne** avec tempête 5 éclairs
4. **ATTENTION**: 800 PV, attaque 10 dégâts feu!
5. **Préparation**: Armure complète, potions de feu
6. **Combat**: Très difficile!
7. **Drops**: 8× Charbon Custom + 2× Diamants
8. **Exploit**: Utilisez ce charbon ultra-puissant!

### 🌡️ Stratégies par Biome

**Nether/Désert**: 
- Placer la torche ici pour moins de faim
- Parfait pour exploitation long terme

**Forêt/Plaines**:
- Zone neutre
- Bonne pour débuter

**Jungle/Marais**:
- À éviter
- Torche consomme trop

---

## ⚙️ Installation

### Prérequis

- **Minecraft 1.20** (ou compatible)
- **Fabric Loader** (≥0.14.0)
- **Fabric API** (latest)

### Étapes

1. **Téléchargez Fabric Loader**
   - URL: https://fabricmc.net/use/installer/
   - Sélectionnez Minecraft 1.20

2. **Installez Fabric API**
   - Téléchargez depuis: https://www.curseforge.com/minecraft/mods/fabric-api
   - Placez le JAR dans `mods/`

3. **Compilez le mod**
   ```bash
   git clone <votre-repo>
   cd Torch-Mod
   ./gradlew build
   ```

4. **Trouvez le JAR compilé**
   - Chemin: `build/libs/livingtorch-1.0.0.jar`

5. **Installez le mod**
   - Placez le JAR dans `~/.minecraft/mods/`

6. **Lancez Minecraft**
   - Profil: Fabric
   - Vérifiez l'installation

### Vérification

- Ouvrez le jeu
- Allez en Survie/Création
- Posez une torche classique
- Si elle devient vivante: ✅ Succès!

---

## 🏗️ Structure du Projet

```
Torch-Mod/
├── src/main/java/com/phibi/livingtorch/
│   ├── LivingTorchMod.java              # Point d'entrée principal
│   ├── entity/
│   │   ├── LivingTorchEntity.java       # Torche vivante (6 PV, 4 états)
│   │   ├── TorchBossEntity.java         # Boss 100 PV (3 blocs)
│   │   ├── InfernoTorchBossEntity.java  # Boss 800 PV (4 blocs) 🔥
│   │   └── TorchState.java              # Énumération 4 états
│   ├── items/
│   │   ├── SoulTorchItem.java           # Torche d'âme (spawn boss)
│   │   └── NormalTorchItem.java         # Torche normale (spawn vivante)
│   ├── util/
│   │   ├── NutritionManager.java        # Système de faim
│   │   └── BiomeHelper.java             # Détection biomes
│   └── events/
│       └── TorchEventHandler.java       # Gestionnaire événements
├── src/main/resources/
│   ├── fabric.mod.json                  # Configuration Fabric
│   ├── assets/livingtorch/
│   │   ├── models/
│   │   │   ├── entity/
│   │   │   │   ├── inferno_torch_boss.json
│   │   │   │   ├── living_torch_normal.json
│   │   │   │   ├── living_torch_weak.json
│   │   │   │   ├── living_torch_excited.json
│   │   │   │   └── living_torch_extinguished.json
│   │   │   └── item/
│   │   │       ├── custom_charcoal.json
│   │   │       ├── charcoal_wood.json
│   │   │       └── living_torch.json
│   │   ├── textures/
│   │   │   ├── entity/
│   │   │   │   ├── inferno_torch_boss.png (64x64)
│   │   │   │   ├── living_torch_normal.png (32x32)
│   │   │   │   ├── living_torch_weak.png (32x32)
│   │   │   │   ├── living_torch_excited.png (32x32)
│   │   │   │   └── living_torch_extinguished.png (32x32)
│   │   │   └── item/
│   │   │       ├── custom_charcoal.png (16x16)
│   │   │       ├── charcoal_wood.png (16x16)
│   │   │       └── living_torch.png (16x16)
│   │   └── lang/
│   │       ├── en_us.json               # Traduction anglais
│   │       └── fr_fr.json               # Traduction français
│   └── TEXTURE_CREATION_GUIDE.md        # Guide création textures
├── build.gradle                         # Configuration Gradle
├── gradle.properties                    # Propriétés build
├── README.md                            # Ce fichier
└── TEXTURE_GUIDE.md                     # Guide rapide textures
```

---

## 📊 Statistiques

### Torche Vivante

```
PV: 6 (3 ❤️)
Faim max: 100
Portée suivi: 32 blocs
Portée attaque: 5 blocs
Lumière: 0-15 (variable)
Dégâts (excitée): 0.5
Feu (excitée): 2 ticks
```

### Torch Boss

```
PV: 100 (50 ❤️)
Attaque: 5 dégâts
Portée: 8 blocs
Hauteur: 2.7 blocs
Largeur: 1.5 blocs
Feu: 30% chance, 2-3 ticks
Barre de boss: Jaune (10 segments)
```

### Inferno Torch Boss

```
PV: 800 (400 ❤️) 🔥
Attaque: 10 dégâts (+ armure × 0.5)
Portée: 8 blocs
Hauteur: 4.0 blocs
Largeur: 2.0 blocs
Feu: Permanent + crée du feu
Armor: 5.0
Barre de boss: Rouge (20 segments)
Knockback: Puissant
Vitesse: 0.35 (rapide)
Rayon d'attaque: Détection toutes les 40 ticks
```

### Drops

```
Torche Vivante:
- 2× Charbon de Bois
- 1× Stick

Torch Boss:
- 4× Charbon Custom
- 2× Charbon classique

Inferno Torch Boss:
- 8× Charbon Custom (ultra-rare!)
- 4× Charbon classique
- 2× Diamants
```

---

## 🎨 Textures avec Blockbench

### À Créer:

1. **Inferno Torch Boss** (64x64)
   - Type: Entity (top-down view)
   - Couleurs: Rouge, Orange, Jaune, Noir
   - Effet: Texture de feu

2. **Living Torch Normal** (32x32)
   - Orange vif avec rayons jaunes
   - Rayons aux 4 côtés

3. **Living Torch Weak** (32x32)
   - Orange sombre, peu de rayons

4. **Living Torch Excited** (32x32)
   - Rouge brillant, 8 rayons puissants
   - Points rouges (texture flammes)

5. **Living Torch Extinguished** (32x32)
   - Gris sombre, aucun rayon
   - Aspect "mort"

6. **Custom Charcoal** (16x16)
   - Noir avec surbrillance or

7. **Charcoal Wood** (16x16)
   - Brun avec grain de bois

8. **Living Torch Item** (16x16)
   - Mini version du état NORMAL

### Placement Blockbench:

```
src/main/resources/assets/livingtorch/textures/
├── entity/
│   ├── inferno_torch_boss.png
│   ├── living_torch_normal.png
│   ├── living_torch_weak.png
│   ├── living_torch_excited.png
│   └── living_torch_extinguished.png
└── item/
    ├── custom_charcoal.png
    ├── charcoal_wood.png
    └── living_torch.png
```

---

## 🚀 Commandes & Commencer

```bash
# Compiler le mod
./gradlew build

# Nettoyer les fichiers générés
./gradlew clean

# Lancer Minecraft en dev
./gradlew runClient

# Exécuter les tests
./gradlew test
```

---

## 📝 Traductions Disponibles

- 🇬🇧 English
- 🇫🇷 Français

---

## 📋 Checklist Complètion

- ✅ Entités (3): Torche Vivante, 2 Boss
- ✅ Système de faim complet
- ✅ Adaptation biomes
- ✅ Spawn mécanique (10% + 1%)
- ✅ Drops personnalisés
- ✅ Modèles JSON
- ⏳ Textures PNG (À créer avec Blockbench)
- ✅ Traductions
- ✅ Documentation

---

## 🐛 Bugs Connus

Aucun actuellement! Signalez tout problème sur GitHub.

---

## 💡 Futures Améliorations

- [ ] Animations personnalisées Blockbench
- [ ] Particules de feu
- [ ] Sons personnalisés
- [ ] Pouvoirs spéciaux états
- [ ] IA plus avancée
- [ ] Configuration MOD
- [ ] Multi-langage avancé

---

## 📄 Version

**v1.0.0** - Release Initiale  
**Minecraft**: 1.20  
**Fabric**: 0.14.0+  
**Auteur**: pHiBi-css  
**Licence**: MIT  

---

## 🎉 Crédits

Merci d'avoir utilisé le Living Torch Mod!  
Amusez-vous bien avec vos torches vivantes! 🔥✨

**Bon jeu!** 🎮
