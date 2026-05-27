# Living Torch Mod 🔥

Un mod Minecraft qui ajoute une torche vivante qui vous suit et que vous devez nourrir, ainsi qu'un mini-boss redoutable !

## 🎮 Contenu Principal

### Torche Vivante 🔥

Une entité loyale qui vous suit et change d'état selon sa faim.

**Caractéristiques:**
- 6 PV (3 ❤️)
- Suit le joueur automatiquement
- Génère de la lumière dynamique
- Fuit l'eau et la pluie
- S'adapte aux biomes

**4 États Disponibles:**

| État | Faim | Lumière | Mouvement | Action |
|------|------|---------|-----------|--------|
| **Éteinte** | 0% | Aucune | Immobile | Morte (reste sur place) |
| **Faible** | 1-30% | Faible (5) | Peu de mouvement | État vulnérable |
| **Normal** | 31-80% | Normal (10) | Normal | État optimal ✓ |
| **Excitée** | 81-100% | Brillante (15) | Très actif | Attaque légère + feu |

**Mécaniques:**
- En état EXCITÉ: attaque les mobs proches (0.5 dégâts) et met le feu (2 ticks)
- Fuit l'eau et la pluie (consomme 2x plus)
- S'adapte au biome: froid = -1 faim/tick, humide = -2 faim/tick

### Torch Boss 👹

Un mini-boss redoutable qui peut spawner aléatoirement.

**Caractéristiques:**
- 100 PV (50 ❤️)
- 3 blocs de haut (1.5 x 2.7)
- Barre de boss jaune avec progression
- Attaque puissante: 5 dégâts + knockback
- Met le feu (30% chance par tick)
- Attaque les mobs proches (3 dégâts)

**Drops:**
- 4x Charbon Personnalisé (temps de cuisson = 2x seau de lave!)
- 2x Charbon classique

## 🍗 Système de Nourriture

La torche vivante a besoin de nourriture pour survivre.

**Aliments Acceptés:**

| Aliment | Faim | Notes |
|---------|------|-------|
| Charbon Custom | +100 | Drop du Torch Boss - TRÈS puissant! |
| Charbon de Bois | +50 | Drop de la torche vivante - Équivalent seau de lave |
| Seau de Lave | +50 | Aliment standard |
| Charbon | +25 | Charbon classique |
| Bois | +10 | Aliment basique |

**Consommation Naturelle:**
- -1 faim/tick (consommation passive)
- -2 faim/tick au contact de l'eau ou sous la pluie
- +1 faim/tick au froid (consomme plus)
- +2 faim/tick en biome humide (jungle/marais)

## 🌍 Adaptation aux Biomes

La torche change de comportement selon le biome:

- **Chaud (Désert, Nether):** Consomme moins, très efficace ✓
- **Froid (Neige, Taïga):** Consomme plus, affaiblie
- **Humide (Jungle, Marais):** Très affaiblie, consomme beaucoup
- **Normal (Forêt, Plaines):** Consommation standard

## 🌱 Spawn & Obtention

### Torche Vivante
**Méthode de spawn:**
- Posez une torche **normale** → 10% chance de spawn une torche vivante
- Apparition accompagnée d'un éclair
- Alterne avec le boss ou spawn classique

**Drops:**
- 2x Charbon de Bois
- 1x Stick

### Torch Boss
**Méthode de spawn:**
- Posez une torche **d'âme** (Soul Torch) → 10% chance de spawn le boss
- Apparition spectaculaire avec 3 éclairs massifs
- Génère une barre de boss jaune

**Mécanisme de spawn (1 torche d'âme = 100% d'action):**
```
10% → Torch Boss + 3 éclairs 👹
10% → Torche Vivante + 1 éclair 🔥
80% → Torche d'âme classique (aucun spawn)
```

## ⚙️ Installation

### Prérequis
1. **Minecraft 1.20** (compatible)
2. **Fabric Loader** (≥0.14.0)
3. **Fabric API** (latest)

### Installation Pas à Pas

1. Téléchargez **Fabric Loader** pour 1.20
   - [fabric.mc/use](https://fabric.mc/use)

2. Installez **Fabric API**
   - Placez dans le dossier `mods/`

3. Compilez le mod:
   ```bash
   ./gradlew build
   ```

4. Le JAR est généré dans `build/libs/`
   - Placez dans `mods/`

5. Lancez Minecraft avec Fabric

## 🎯 Guide de Jeu

### Démarrage
1. Posez une **torche normale** → spawn possible d'une torche vivante
2. Nourrissez-la avec du charbon ou du bois
3. Gardez-la nourrie pour la garder active (état NORMAL)

### Combat contre le Torch Boss
1. Posez une **torche d'âme** pour avoir 10% de chance
2. Le boss apparaîtra avec 3 éclairs massifs
3. Afrontez-le: 100 PV, attaque forte
4. Récupérez le **charbon custom** drop (2x seau de lave!)
5. Utilisez-le pour nourrir votre torche vivante

### Optimisation
- **Biome chaud:** Placez la torche au Nether ou au Désert pour moins de consommation
- **Nourriture:** Cherchez des Torch Boss pour obtenir le charbon custom ultra-puissant
- **Défense:** État EXCITÉ = attaque auto des mobs (utile en grotte!)

## 📊 Statistiques

**Torche Vivante:**
- Faim max: 100
- PV: 6 (3 ❤️)
- Portée de suivi: 32 blocs
- Génération de lumière: 0-15 (selon l'état)

**Torch Boss:**
- Faim max: 100
- PV: 100 (50 ❤️)
- Attaque: 5 dégâts
- Portée: 32 blocs
- Hauteur: 2.7 blocs
- Largeur: 1.5 blocs

## 🛠️ Développement

**Structure du Projet:**
```
src/main/java/com/phibi/livingtorch/
├── LivingTorchMod.java              # Point d'entrée
├── entity/
│   ├── LivingTorchEntity.java       # Torche vivante
│   ├── TorchBossEntity.java         # Mini-boss
│   └── TorchState.java              # Énumération états
├── items/
│   └── SoulTorchItem.java           # Item torche d'âme
├── util/
│   ├── NutritionManager.java        # Gestion nourriture
│   └── BiomeHelper.java             # Adaptation biomes
└── events/
    └── TorchEventHandler.java       # Gestionnaire d'événements
```

## 📝 Version
- **v1.0.0** - Release initiale
- Minecraft: 1.20
- Fabric: 0.14.0+

## 👤 Auteur
**pHiBi-css**

## 📄 Licence
MIT

---

**Amusez-vous bien avec votre torche vivante! 🔥✨**
