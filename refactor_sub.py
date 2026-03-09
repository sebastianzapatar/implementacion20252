import os
import shutil

base_dir = '/Users/sebastianzapata/Documents/trabajo pruebas/implementacion20252/src/main/java/com/sindocker/sindocker'
test_dir = '/Users/sebastianzapata/Documents/trabajo pruebas/implementacion20252/src/test/java/com/sindocker/sindocker'

# Definitions of moves inside feature packages
subpackages = ['controllers', 'services', 'dao', 'models', 'dto']

moves = [
    # mediano
    ('mediano/MedianoController.java', 'mediano/controllers/MedianoController.java'),
    ('mediano/ServiceMediano.java', 'mediano/services/ServiceMediano.java'),
    ('mediano/IServiceMediano.java', 'mediano/services/IServiceMediano.java'),
    ('mediano/IMedianoDao.java', 'mediano/dao/IMedianoDao.java'),
    ('mediano/Mediano.java', 'mediano/models/Mediano.java'),
    ('mediano/MedianoDTO.java', 'mediano/dto/MedianoDTO.java'),
    
    # photo
    ('photo/PhotoController.java', 'photo/controllers/PhotoController.java'),
    ('photo/ServicePhoto.java', 'photo/services/ServicePhoto.java'),
    ('photo/IServicePhoto.java', 'photo/services/IServicePhoto.java'),
    ('photo/IPhotosDao.java', 'photo/dao/IPhotosDao.java'),
    ('photo/Photo.java', 'photo/models/Photo.java'),
    ('photo/PhotoDTO.java', 'photo/dto/PhotoDTO.java'),
]

# Ensure directories exist
for feat in ['mediano', 'photo']:
    for sub in subpackages:
        os.makedirs(os.path.join(base_dir, feat, sub), exist_ok=True)

# Move files
for src, dst in moves:
    src_path = os.path.join(base_dir, src)
    dst_path = os.path.join(base_dir, dst)
    if os.path.exists(src_path):
        os.rename(src_path, dst_path)

replacements = {
    # packages
    'package com.sindocker.sindocker.mediano;': 'package com.sindocker.sindocker.mediano.{sub};',
    'package com.sindocker.sindocker.photo;': 'package com.sindocker.sindocker.photo.{sub};',
    
    # mediano imports
    'import com.sindocker.sindocker.mediano.MedianoController;': 'import com.sindocker.sindocker.mediano.controllers.MedianoController;',
    'import com.sindocker.sindocker.mediano.ServiceMediano;': 'import com.sindocker.sindocker.mediano.services.ServiceMediano;',
    'import com.sindocker.sindocker.mediano.IServiceMediano;': 'import com.sindocker.sindocker.mediano.services.IServiceMediano;',
    'import com.sindocker.sindocker.mediano.IMedianoDao;': 'import com.sindocker.sindocker.mediano.dao.IMedianoDao;',
    'import com.sindocker.sindocker.mediano.Mediano;': 'import com.sindocker.sindocker.mediano.models.Mediano;',
    'import com.sindocker.sindocker.mediano.MedianoDTO;': 'import com.sindocker.sindocker.mediano.dto.MedianoDTO;',
    
    # photo imports
    'import com.sindocker.sindocker.photo.PhotoController;': 'import com.sindocker.sindocker.photo.controllers.PhotoController;',
    'import com.sindocker.sindocker.photo.ServicePhoto;': 'import com.sindocker.sindocker.photo.services.ServicePhoto;',
    'import com.sindocker.sindocker.photo.IServicePhoto;': 'import com.sindocker.sindocker.photo.services.IServicePhoto;',
    'import com.sindocker.sindocker.photo.IPhotosDao;': 'import com.sindocker.sindocker.photo.dao.IPhotosDao;',
    'import com.sindocker.sindocker.photo.Photo;': 'import com.sindocker.sindocker.photo.models.Photo;',
    'import com.sindocker.sindocker.photo.PhotoDTO;': 'import com.sindocker.sindocker.photo.dto.PhotoDTO;',
}

def process_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()

    # Apply general import replacements
    for old, new in replacements.items():
        if not old.startswith('package '):
            content = content.replace(old, new)

    # Package declaration specific processing
    if filepath.endswith('.java'):
        rel_path = os.path.relpath(filepath, base_dir)
        parts = rel_path.split(os.sep)
        if len(parts) >= 3 and parts[0] in ['mediano', 'photo'] and parts[1] in subpackages: # feature/sub/Class.java
            old_pkg = f'package com.sindocker.sindocker.{parts[0]};'
            new_pkg = f'package com.sindocker.sindocker.{parts[0]}.{parts[1]};'
            content = content.replace(old_pkg, new_pkg)

    with open(filepath, 'w') as f:
        f.write(content)

# Process all Java files in main
for root, dirs, files in os.walk(base_dir):
    for file in files:
        if file.endswith('.java'):
            process_file(os.path.join(root, file))

# Process all Java files in test
for root, dirs, files in os.walk(test_dir):
    for file in files:
        if file.endswith('.java'):
            process_file(os.path.join(root, file))

print("Sub-folder refactoring complete.")
