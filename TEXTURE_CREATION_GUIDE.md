# 🎨 Guide Complet de Création des Textures

## 🌐 Outils Recommandés (GRATUIT)

### 1. **CreateTextures** (MEILLEUR pour Minecraft)
- URL: https://createtextures.com/
- ✅ Éditeur spécialisé Minecraft
- ✅ Prévisualisation 3D en temps réel
- ✅ Export PNG direct
- ✅ Pas de login requis

### 2. **Pixilart**
- URL: https://www.pixilart.com/draw
- ✅ Pixel art gratuit
- ✅ Export PNG transparent
- ✅ Facile à utiliser

### 3. **Piskel**
- URL: https://www.piskelapp.com/
- ✅ Sprite editor gratuit
- ✅ Support des animations
- ✅ Transparent PNG

---

## 📐 Dimensions et Format

### Entités (Top-Down View):
- **Inferno Torch Boss**: 64x64 px
- **Living Torch States**: 32x32 px (chaque état)

### Items:
- **Custom Charcoal**: 16x16 px
- **Charcoal Wood**: 16x16 px
- **Living Torch Item**: 16x16 px

### Format:
- **Type**: PNG avec fond transparent
- **Palette**: RGB ou RGBA
- **Compression**: Activée

---

## 🎨 TEXTURES À CRÉER

### 1️⃣ INFERNO TORCH BOSS (64x64)

**Concept**: Boss de feu massif, 4 blocs de haut

**Couleurs**:
```
Rouge feu:     #FF3333
Orange feu:    #FF8800
Jaune feu:     #FFDD00
Noir ombre:    #1a1a1a
Gris contour:  #333333
```

**Structure**:
```
Centre (32x32):
- Corps: Carré rouge 20x20
- Contour noir 2px

Les 4 coins:
- Losange orange (8x8)
- Symbolise les flammes

Centre du centre:
- Carré jaune 8x8 (cœur du feu)
- 2px de contour noir

Effets:
- Dégradé du rouge au jaune
- Points noirs aléatoires pour la texture
```

**Étapes CreateTextures**:
1. Nouveau 64x64
2. Remplir fond: Transparent
3. Pinceau rouge (#FF3333): Tracer carré 20x20 au centre
4. Pinceau noir: Contour 2px
5. Pinceau orange: 4 losanges aux coins
6. Pinceau jaune: Carré 8x8 au centre
7. Ajouter pixels noirs aléatoires
8. Export PNG

---

### 2️⃣ LIVING TORCH - 4 ÉTATS (32x32 chacun)

#### STATE 1: EXTINGUISHED (Éteinte)
**Fichier**: `living_torch_extinguished.png`

```
Couleurs:
Noir:  #1a1a1a
Gris:  #404040

Structure:
- Silhouette grise éteinte
- Pas de lumière
- Tête: Cercle gris 8x8
- Corps: Rectangles gris 6x14
- Contours noirs 1px
- Aspect "mort" et sombre
```

#### STATE 2: WEAK (Faible)
**Fichier**: `living_torch_weak.png`

```
Couleurs:
Orange sombre: #CC5500
Jaune faible:  #FFAA00
Noir:          #1a1a1a

Structure:
- Même silhouette que NORMAL
- Mais couleurs moins brillantes
- Tête: Orange cercle 8x8
- Corps: Orange rectangles 6x14
- Surbrillance jaune faible (petits points)
- Pas de rayons lumineux
```

#### STATE 3: NORMAL (Normal) ⭐ État Optimal
**Fichier**: `living_torch_normal.png`

```
Couleurs:
Orange:    #FF6600
Jaune:     #FFDD00
Rouge:     #FF4444
Noir:      #1a1a1a

Structure (32x32):
- Tête (haut): Cercle 10x10
  - Orange #FF6600
  - Bordure noire 1px
  - Point jaune au centre (2x2)

- Corps (milieu): Rectangles empilés 8x16
  - Base orange #FF6600
  - Surbrillance jaune (côté droit)

- Base (bas): Carré 6x6
  - Noir avec contour

- Rayons lumineux (optionnel):
  - 4 traits jaunes #FFDD00
  - 1px large
  - Aux 4 côtés
  - Longueur 3-4px
```

#### STATE 4: EXCITED (Excitée) 🔥 État Surcharge
**Fichier**: `living_torch_excited.png`

```
Couleurs:
Rouge brillant: #FF2222
Jaune bright:   #FFFF00
Orange:         #FF8800
Noir:           #1a1a1a

Structure (32x32):
- Tête (haut): Cercle 12x12
  - Rouge brillant #FF2222
  - Bordure noire 1px
  - 2 points jaunes (yeux)

- Corps (milieu): Rectangles empilés 10x14
  - Dégradé rouge→orange
  - Surbrillance jaune large (2-3px côté)
  - Points rouges aléatoires (texture flammes)

- Base (bas): Carré 8x8
  - Orange avec contour noir

- Rayons lumineux PUISSANTS:
  - 8 traits jaune/orange
  - 2px large
  - Dans les 8 directions (cardinales + diagonales)
  - Longueur 5-6px chacun
  - Alternance jaune/orange
```

---

### 3️⃣ CUSTOM CHARCOAL (16x16)

```
Couleurs:
Noir foncé:   #0a0a0a
Noir:         #1a1a1a
Or:           #FFD700
Or sombre:    #DAA520

Structure:
- Carré 12x12 centré
- Base noire #1a1a1a
- Surbrillance or (#FFD700) coins haut-droit
- Détails or en diagonale (3-4 pixels)
- Contour or (#DAA520) 1px
- Texture: Points noirs aléatoires

Aspect:
- Charbon avec effet précieux
- Brille avec teinte dorée
```

---

### 4️⃣ CHARCOAL WOOD (16x16)

```
Couleurs:
Brun foncé:  #3d2817
Brun:        #5c4033
Brun clair:  #8b7355
Noir:        #1a1a1a

Structure:
- Carré 12x12 centré
- Base brun #5c4033
- Grain de bois vertical (lignes brun clair)
- Espacement: 2-3 pixels
- Contour noir 1px
- Surbrillance brun clair côté haut-droit

Aspect:
- Charbon avec texture bois
- Aspect naturel
```

---

### 5️⃣ LIVING TORCH ITEM (16x16)

```
Couleurs:
Orange:    #FF6600
Jaune:     #FFDD00
Noir:      #1a1a1a

Structure:
- Tête: Cercle 4x4 (haut)
  - Orange #FF6600
  - Point jaune centre
  
- Corps: Rectangle 3x8 (milieu)
  - Orange avec surbrillance jaune
  
- Base: Carré 2x2 (bas)
  - Noir
  
- Contours: Noir 0.5px
```

---

## 🛠️ ÉTAPES PAR ÉTAPES - CreateTextures

### Pour chaque texture:

1. **Accédez à**: https://createtextures.com/
2. **Cliquez** "Create New"
3. **Sélectionnez** la dimension (64x64, 32x32, ou 16x16)
4. **Sélectionnez** "Transparent" comme fond
5. **Dessinez** selon les spécifications ci-dessus
6. **Utilisez** l'outil "Pencil" pour les pixels individuels
7. **Utilisez** "Fill" pour les zones larges
8. **Testez** en 3D si disponible
9. **Exportez** en PNG
10. **Sauvegardez** dans le bon dossier

---

## 📁 Dossiers de Destination

```
src/main/resources/assets/livingtorch/textures/
├── entity/
│   ├── inferno_torch_boss.png (64x64)
│   └── living_torch_extinguished.png (32x32)
│   └── living_torch_weak.png (32x32)
│   └── living_torch_normal.png (32x32)
│   └── living_torch_excited.png (32x32)
└── item/
    ├── custom_charcoal.png (16x16)
    ├── charcoal_wood.png (16x16)
    └── living_torch.png (16x16)
```

---

## ✅ Checklist

- [ ] Inferno Torch Boss (64x64)
- [ ] Living Torch Extinguished (32x32)
- [ ] Living Torch Weak (32x32)
- [ ] Living Torch Normal (32x32)
- [ ] Living Torch Excited (32x32)
- [ ] Custom Charcoal (16x16)
- [ ] Charcoal Wood (16x16)
- [ ] Living Torch Item (16x16)
- [ ] Tous les fichiers en PNG
- [ ] Tous les fichiers au bon endroit

---

## 🎯 Conseils Finaux

✨ **Utiliser une grille** pour aligner les pixels  
✨ **Zoom à 200-400%** pour bien voir les pixels  
✨ **Tester les couleurs** en jouant avec la saturation  
✨ **Faire des brouillons** avant la version finale  
✨ **Exporter en PNG** avec fond transparent  
✨ **Vérifier les dimensions** avant export  

---

**Bonne création! 🎨✨**
