import os

base_dir = '/Users/sebastianzapata/Documents/trabajo pruebas/implementacion20252/src/main/java/com/sindocker/sindocker'
test_dir = '/Users/sebastianzapata/Documents/trabajo pruebas/implementacion20252/src/test/java/com/sindocker/sindocker'

moves = [
    ('controllers/MedianoController.java', 'mediano/MedianoController.java'),
    ('dao/IMedianoDao.java', 'mediano/IMedianoDao.java'),
    ('dto/MedianoDTO.java', 'mediano/MedianoDTO.java'),
    ('models/Mediano.java', 'mediano/Mediano.java'),
    ('services/IServiceMediano.java', 'mediano/IServiceMediano.java'),
    ('services/ServiceMediano.java', 'mediano/ServiceMediano.java'),
    ('controllers/PhotoController.java', 'photo/PhotoController.java'),
    ('dao/IPhotosDao.java', 'photo/IPhotosDao.java'),
    ('dto/PhotoDTO.java', 'photo/PhotoDTO.java'),
    ('models/Photo.java', 'photo/Photo.java'),
    ('services/IServicePhoto.java', 'photo/IServicePhoto.java'),
    ('services/ServicePhoto.java', 'photo/ServicePhoto.java'),
    ('Excepciones/NameException.java', 'exception/NameException.java'),
    ('controllers/GlobalExceptionHandler.java', 'exception/GlobalExceptionHandler.java'),
]

# Move files
for src, dst in moves:
    src_path = os.path.join(base_dir, src)
    dst_path = os.path.join(base_dir, dst)
    if os.path.exists(src_path):
        os.rename(src_path, dst_path)

# Delete empty dirs
for d in ['controllers', 'dao', 'dto', 'models', 'services', 'Excepciones']:
    dir_path = os.path.join(base_dir, d)
    if os.path.exists(dir_path):
        try:
            os.rmdir(dir_path)
        except OSError:
            pass

replacements = {
    'package com.sindocker.sindocker.controllers;': '',
    'package com.sindocker.sindocker.dao;': '',
    'package com.sindocker.sindocker.dto;': '',
    'package com.sindocker.sindocker.models;': '',
    'package com.sindocker.sindocker.services;': '',
    'package com.sindocker.sindocker.Excepciones;': '',
    'import com.sindocker.sindocker.Excepciones.NameException;': 'import com.sindocker.sindocker.exception.NameException;',
    
    'import com.sindocker.sindocker.models.Mediano;': 'import com.sindocker.sindocker.mediano.Mediano;',
    'import com.sindocker.sindocker.dto.MedianoDTO;': 'import com.sindocker.sindocker.mediano.MedianoDTO;',
    'import com.sindocker.sindocker.dao.IMedianoDao;': 'import com.sindocker.sindocker.mediano.IMedianoDao;',
    'import com.sindocker.sindocker.services.IServiceMediano;': 'import com.sindocker.sindocker.mediano.IServiceMediano;',
    'import com.sindocker.sindocker.services.ServiceMediano;': 'import com.sindocker.sindocker.mediano.ServiceMediano;',
    'import com.sindocker.sindocker.controllers.MedianoController;': 'import com.sindocker.sindocker.mediano.MedianoController;',
    
    'import com.sindocker.sindocker.models.Photo;': 'import com.sindocker.sindocker.photo.Photo;',
    'import com.sindocker.sindocker.dto.PhotoDTO;': 'import com.sindocker.sindocker.photo.PhotoDTO;',
    'import com.sindocker.sindocker.dao.IPhotosDao;': 'import com.sindocker.sindocker.photo.IPhotosDao;',
    'import com.sindocker.sindocker.services.IServicePhoto;': 'import com.sindocker.sindocker.photo.IServicePhoto;',
    'import com.sindocker.sindocker.controllers.PhotoController;': 'import com.sindocker.sindocker.photo.PhotoController;',
}

def process_file(filepath, pkg_name=None):
    with open(filepath, 'r') as f:
        content = f.read()
    
    for old, new in replacements.items():
        if old.startswith('package '):
            if pkg_name:
                content = content.replace(old, f'package com.sindocker.sindocker.{pkg_name};')
        else:
            content = content.replace(old, new)
            
    with open(filepath, 'w') as f:
        f.write(content)

# Process main files
for root, dirs, files in os.walk(base_dir):
    for file in files:
        if file.endswith('.java'):
            filepath = os.path.join(root, file)
            # determine package name from directory
            rel_path = os.path.relpath(root, base_dir)
            pkg_name = rel_path.replace(os.sep, '.') if rel_path != '.' else None
            process_file(filepath, pkg_name)

# Process test files
for root, dirs, files in os.walk(test_dir):
    for file in files:
        if file.endswith('.java'):
            filepath = os.path.join(root, file)
            process_file(filepath)

print("Refactoring complete.")
